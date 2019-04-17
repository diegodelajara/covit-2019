package com.covit.web.rest;

import com.covit.CovitApp;

import com.covit.domain.Tower;
import com.covit.repository.TowerRepository;
import com.covit.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.covit.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TowerResource REST controller.
 *
 * @see TowerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CovitApp.class)
public class TowerResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    @Autowired
    private TowerRepository towerRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTowerMockMvc;

    private Tower tower;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TowerResource towerResource = new TowerResource(towerRepository);
        this.restTowerMockMvc = MockMvcBuilders.standaloneSetup(towerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tower createEntity(EntityManager em) {
        Tower tower = new Tower()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER);
        return tower;
    }

    @Before
    public void initTest() {
        tower = createEntity(em);
    }

    @Test
    @Transactional
    public void createTower() throws Exception {
        int databaseSizeBeforeCreate = towerRepository.findAll().size();

        // Create the Tower
        restTowerMockMvc.perform(post("/api/towers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tower)))
            .andExpect(status().isCreated());

        // Validate the Tower in the database
        List<Tower> towerList = towerRepository.findAll();
        assertThat(towerList).hasSize(databaseSizeBeforeCreate + 1);
        Tower testTower = towerList.get(towerList.size() - 1);
        assertThat(testTower.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTower.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTower.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void createTowerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = towerRepository.findAll().size();

        // Create the Tower with an existing ID
        tower.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTowerMockMvc.perform(post("/api/towers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tower)))
            .andExpect(status().isBadRequest());

        // Validate the Tower in the database
        List<Tower> towerList = towerRepository.findAll();
        assertThat(towerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTowers() throws Exception {
        // Initialize the database
        towerRepository.saveAndFlush(tower);

        // Get all the towerList
        restTowerMockMvc.perform(get("/api/towers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tower.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())));
    }
    
    @Test
    @Transactional
    public void getTower() throws Exception {
        // Initialize the database
        towerRepository.saveAndFlush(tower);

        // Get the tower
        restTowerMockMvc.perform(get("/api/towers/{id}", tower.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tower.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTower() throws Exception {
        // Get the tower
        restTowerMockMvc.perform(get("/api/towers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTower() throws Exception {
        // Initialize the database
        towerRepository.saveAndFlush(tower);

        int databaseSizeBeforeUpdate = towerRepository.findAll().size();

        // Update the tower
        Tower updatedTower = towerRepository.findById(tower.getId()).get();
        // Disconnect from session so that the updates on updatedTower are not directly saved in db
        em.detach(updatedTower);
        updatedTower
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER);

        restTowerMockMvc.perform(put("/api/towers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTower)))
            .andExpect(status().isOk());

        // Validate the Tower in the database
        List<Tower> towerList = towerRepository.findAll();
        assertThat(towerList).hasSize(databaseSizeBeforeUpdate);
        Tower testTower = towerList.get(towerList.size() - 1);
        assertThat(testTower.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTower.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTower.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingTower() throws Exception {
        int databaseSizeBeforeUpdate = towerRepository.findAll().size();

        // Create the Tower

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTowerMockMvc.perform(put("/api/towers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tower)))
            .andExpect(status().isBadRequest());

        // Validate the Tower in the database
        List<Tower> towerList = towerRepository.findAll();
        assertThat(towerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTower() throws Exception {
        // Initialize the database
        towerRepository.saveAndFlush(tower);

        int databaseSizeBeforeDelete = towerRepository.findAll().size();

        // Delete the tower
        restTowerMockMvc.perform(delete("/api/towers/{id}", tower.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tower> towerList = towerRepository.findAll();
        assertThat(towerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tower.class);
        Tower tower1 = new Tower();
        tower1.setId(1L);
        Tower tower2 = new Tower();
        tower2.setId(tower1.getId());
        assertThat(tower1).isEqualTo(tower2);
        tower2.setId(2L);
        assertThat(tower1).isNotEqualTo(tower2);
        tower1.setId(null);
        assertThat(tower1).isNotEqualTo(tower2);
    }
}

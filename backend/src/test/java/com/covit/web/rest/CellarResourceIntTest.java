package com.covit.web.rest;

import com.covit.CovitApp;

import com.covit.domain.Cellar;
import com.covit.repository.CellarRepository;
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
 * Test class for the CellarResource REST controller.
 *
 * @see CellarResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CovitApp.class)
public class CellarResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DIMENSIONS = "AAAAAAAAAA";
    private static final String UPDATED_DIMENSIONS = "BBBBBBBBBB";

    @Autowired
    private CellarRepository cellarRepository;

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

    private MockMvc restCellarMockMvc;

    private Cellar cellar;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CellarResource cellarResource = new CellarResource(cellarRepository);
        this.restCellarMockMvc = MockMvcBuilders.standaloneSetup(cellarResource)
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
    public static Cellar createEntity(EntityManager em) {
        Cellar cellar = new Cellar()
            .name(DEFAULT_NAME)
            .dimensions(DEFAULT_DIMENSIONS);
        return cellar;
    }

    @Before
    public void initTest() {
        cellar = createEntity(em);
    }

    @Test
    @Transactional
    public void createCellar() throws Exception {
        int databaseSizeBeforeCreate = cellarRepository.findAll().size();

        // Create the Cellar
        restCellarMockMvc.perform(post("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cellar)))
            .andExpect(status().isCreated());

        // Validate the Cellar in the database
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeCreate + 1);
        Cellar testCellar = cellarList.get(cellarList.size() - 1);
        assertThat(testCellar.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCellar.getDimensions()).isEqualTo(DEFAULT_DIMENSIONS);
    }

    @Test
    @Transactional
    public void createCellarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cellarRepository.findAll().size();

        // Create the Cellar with an existing ID
        cellar.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCellarMockMvc.perform(post("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cellar)))
            .andExpect(status().isBadRequest());

        // Validate the Cellar in the database
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCellars() throws Exception {
        // Initialize the database
        cellarRepository.saveAndFlush(cellar);

        // Get all the cellarList
        restCellarMockMvc.perform(get("/api/cellars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cellar.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].dimensions").value(hasItem(DEFAULT_DIMENSIONS.toString())));
    }
    
    @Test
    @Transactional
    public void getCellar() throws Exception {
        // Initialize the database
        cellarRepository.saveAndFlush(cellar);

        // Get the cellar
        restCellarMockMvc.perform(get("/api/cellars/{id}", cellar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cellar.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.dimensions").value(DEFAULT_DIMENSIONS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCellar() throws Exception {
        // Get the cellar
        restCellarMockMvc.perform(get("/api/cellars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCellar() throws Exception {
        // Initialize the database
        cellarRepository.saveAndFlush(cellar);

        int databaseSizeBeforeUpdate = cellarRepository.findAll().size();

        // Update the cellar
        Cellar updatedCellar = cellarRepository.findById(cellar.getId()).get();
        // Disconnect from session so that the updates on updatedCellar are not directly saved in db
        em.detach(updatedCellar);
        updatedCellar
            .name(UPDATED_NAME)
            .dimensions(UPDATED_DIMENSIONS);

        restCellarMockMvc.perform(put("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCellar)))
            .andExpect(status().isOk());

        // Validate the Cellar in the database
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeUpdate);
        Cellar testCellar = cellarList.get(cellarList.size() - 1);
        assertThat(testCellar.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCellar.getDimensions()).isEqualTo(UPDATED_DIMENSIONS);
    }

    @Test
    @Transactional
    public void updateNonExistingCellar() throws Exception {
        int databaseSizeBeforeUpdate = cellarRepository.findAll().size();

        // Create the Cellar

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCellarMockMvc.perform(put("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cellar)))
            .andExpect(status().isBadRequest());

        // Validate the Cellar in the database
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCellar() throws Exception {
        // Initialize the database
        cellarRepository.saveAndFlush(cellar);

        int databaseSizeBeforeDelete = cellarRepository.findAll().size();

        // Delete the cellar
        restCellarMockMvc.perform(delete("/api/cellars/{id}", cellar.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cellar.class);
        Cellar cellar1 = new Cellar();
        cellar1.setId(1L);
        Cellar cellar2 = new Cellar();
        cellar2.setId(cellar1.getId());
        assertThat(cellar1).isEqualTo(cellar2);
        cellar2.setId(2L);
        assertThat(cellar1).isNotEqualTo(cellar2);
        cellar1.setId(null);
        assertThat(cellar1).isNotEqualTo(cellar2);
    }
}

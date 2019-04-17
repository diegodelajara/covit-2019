package com.covit.web.rest;

import com.covit.CovitApp;

import com.covit.domain.Condominium;
import com.covit.repository.CondominiumRepository;
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
 * Test class for the CondominiumResource REST controller.
 *
 * @see CondominiumResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CovitApp.class)
public class CondominiumResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COMMUNE = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_ONE = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_ONE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_TWO = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_TWO = "BBBBBBBBBB";

    @Autowired
    private CondominiumRepository condominiumRepository;

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

    private MockMvc restCondominiumMockMvc;

    private Condominium condominium;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CondominiumResource condominiumResource = new CondominiumResource(condominiumRepository);
        this.restCondominiumMockMvc = MockMvcBuilders.standaloneSetup(condominiumResource)
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
    public static Condominium createEntity(EntityManager em) {
        Condominium condominium = new Condominium()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .commune(DEFAULT_COMMUNE)
            .contactPerson(DEFAULT_CONTACT_PERSON)
            .mail(DEFAULT_MAIL)
            .numberOne(DEFAULT_NUMBER_ONE)
            .numberTwo(DEFAULT_NUMBER_TWO);
        return condominium;
    }

    @Before
    public void initTest() {
        condominium = createEntity(em);
    }

    @Test
    @Transactional
    public void createCondominium() throws Exception {
        int databaseSizeBeforeCreate = condominiumRepository.findAll().size();

        // Create the Condominium
        restCondominiumMockMvc.perform(post("/api/condominiums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(condominium)))
            .andExpect(status().isCreated());

        // Validate the Condominium in the database
        List<Condominium> condominiumList = condominiumRepository.findAll();
        assertThat(condominiumList).hasSize(databaseSizeBeforeCreate + 1);
        Condominium testCondominium = condominiumList.get(condominiumList.size() - 1);
        assertThat(testCondominium.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCondominium.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCondominium.getCommune()).isEqualTo(DEFAULT_COMMUNE);
        assertThat(testCondominium.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testCondominium.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testCondominium.getNumberOne()).isEqualTo(DEFAULT_NUMBER_ONE);
        assertThat(testCondominium.getNumberTwo()).isEqualTo(DEFAULT_NUMBER_TWO);
    }

    @Test
    @Transactional
    public void createCondominiumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = condominiumRepository.findAll().size();

        // Create the Condominium with an existing ID
        condominium.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCondominiumMockMvc.perform(post("/api/condominiums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(condominium)))
            .andExpect(status().isBadRequest());

        // Validate the Condominium in the database
        List<Condominium> condominiumList = condominiumRepository.findAll();
        assertThat(condominiumList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCondominiums() throws Exception {
        // Initialize the database
        condominiumRepository.saveAndFlush(condominium);

        // Get all the condominiumList
        restCondominiumMockMvc.perform(get("/api/condominiums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(condominium.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].commune").value(hasItem(DEFAULT_COMMUNE.toString())))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].numberOne").value(hasItem(DEFAULT_NUMBER_ONE.toString())))
            .andExpect(jsonPath("$.[*].numberTwo").value(hasItem(DEFAULT_NUMBER_TWO.toString())));
    }
    
    @Test
    @Transactional
    public void getCondominium() throws Exception {
        // Initialize the database
        condominiumRepository.saveAndFlush(condominium);

        // Get the condominium
        restCondominiumMockMvc.perform(get("/api/condominiums/{id}", condominium.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(condominium.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.commune").value(DEFAULT_COMMUNE.toString()))
            .andExpect(jsonPath("$.contactPerson").value(DEFAULT_CONTACT_PERSON.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.numberOne").value(DEFAULT_NUMBER_ONE.toString()))
            .andExpect(jsonPath("$.numberTwo").value(DEFAULT_NUMBER_TWO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCondominium() throws Exception {
        // Get the condominium
        restCondominiumMockMvc.perform(get("/api/condominiums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCondominium() throws Exception {
        // Initialize the database
        condominiumRepository.saveAndFlush(condominium);

        int databaseSizeBeforeUpdate = condominiumRepository.findAll().size();

        // Update the condominium
        Condominium updatedCondominium = condominiumRepository.findById(condominium.getId()).get();
        // Disconnect from session so that the updates on updatedCondominium are not directly saved in db
        em.detach(updatedCondominium);
        updatedCondominium
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .commune(UPDATED_COMMUNE)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .mail(UPDATED_MAIL)
            .numberOne(UPDATED_NUMBER_ONE)
            .numberTwo(UPDATED_NUMBER_TWO);

        restCondominiumMockMvc.perform(put("/api/condominiums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCondominium)))
            .andExpect(status().isOk());

        // Validate the Condominium in the database
        List<Condominium> condominiumList = condominiumRepository.findAll();
        assertThat(condominiumList).hasSize(databaseSizeBeforeUpdate);
        Condominium testCondominium = condominiumList.get(condominiumList.size() - 1);
        assertThat(testCondominium.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCondominium.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCondominium.getCommune()).isEqualTo(UPDATED_COMMUNE);
        assertThat(testCondominium.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testCondominium.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testCondominium.getNumberOne()).isEqualTo(UPDATED_NUMBER_ONE);
        assertThat(testCondominium.getNumberTwo()).isEqualTo(UPDATED_NUMBER_TWO);
    }

    @Test
    @Transactional
    public void updateNonExistingCondominium() throws Exception {
        int databaseSizeBeforeUpdate = condominiumRepository.findAll().size();

        // Create the Condominium

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCondominiumMockMvc.perform(put("/api/condominiums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(condominium)))
            .andExpect(status().isBadRequest());

        // Validate the Condominium in the database
        List<Condominium> condominiumList = condominiumRepository.findAll();
        assertThat(condominiumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCondominium() throws Exception {
        // Initialize the database
        condominiumRepository.saveAndFlush(condominium);

        int databaseSizeBeforeDelete = condominiumRepository.findAll().size();

        // Delete the condominium
        restCondominiumMockMvc.perform(delete("/api/condominiums/{id}", condominium.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Condominium> condominiumList = condominiumRepository.findAll();
        assertThat(condominiumList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Condominium.class);
        Condominium condominium1 = new Condominium();
        condominium1.setId(1L);
        Condominium condominium2 = new Condominium();
        condominium2.setId(condominium1.getId());
        assertThat(condominium1).isEqualTo(condominium2);
        condominium2.setId(2L);
        assertThat(condominium1).isNotEqualTo(condominium2);
        condominium1.setId(null);
        assertThat(condominium1).isNotEqualTo(condominium2);
    }
}

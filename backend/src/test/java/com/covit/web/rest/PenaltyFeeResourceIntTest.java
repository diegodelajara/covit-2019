package com.covit.web.rest;

import com.covit.CovitApp;

import com.covit.domain.PenaltyFee;
import com.covit.repository.PenaltyFeeRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.covit.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PenaltyFeeResource REST controller.
 *
 * @see PenaltyFeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CovitApp.class)
public class PenaltyFeeResourceIntTest {

    private static final String DEFAULT_CONCEPT = "AAAAAAAAAA";
    private static final String UPDATED_CONCEPT = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final LocalDate DEFAULT_REGISTRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private PenaltyFeeRepository penaltyFeeRepository;

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

    private MockMvc restPenaltyFeeMockMvc;

    private PenaltyFee penaltyFee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PenaltyFeeResource penaltyFeeResource = new PenaltyFeeResource(penaltyFeeRepository);
        this.restPenaltyFeeMockMvc = MockMvcBuilders.standaloneSetup(penaltyFeeResource)
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
    public static PenaltyFee createEntity(EntityManager em) {
        PenaltyFee penaltyFee = new PenaltyFee()
            .concept(DEFAULT_CONCEPT)
            .amount(DEFAULT_AMOUNT)
            .registrationDate(DEFAULT_REGISTRATION_DATE);
        return penaltyFee;
    }

    @Before
    public void initTest() {
        penaltyFee = createEntity(em);
    }

    @Test
    @Transactional
    public void createPenaltyFee() throws Exception {
        int databaseSizeBeforeCreate = penaltyFeeRepository.findAll().size();

        // Create the PenaltyFee
        restPenaltyFeeMockMvc.perform(post("/api/penalty-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(penaltyFee)))
            .andExpect(status().isCreated());

        // Validate the PenaltyFee in the database
        List<PenaltyFee> penaltyFeeList = penaltyFeeRepository.findAll();
        assertThat(penaltyFeeList).hasSize(databaseSizeBeforeCreate + 1);
        PenaltyFee testPenaltyFee = penaltyFeeList.get(penaltyFeeList.size() - 1);
        assertThat(testPenaltyFee.getConcept()).isEqualTo(DEFAULT_CONCEPT);
        assertThat(testPenaltyFee.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPenaltyFee.getRegistrationDate()).isEqualTo(DEFAULT_REGISTRATION_DATE);
    }

    @Test
    @Transactional
    public void createPenaltyFeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = penaltyFeeRepository.findAll().size();

        // Create the PenaltyFee with an existing ID
        penaltyFee.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPenaltyFeeMockMvc.perform(post("/api/penalty-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(penaltyFee)))
            .andExpect(status().isBadRequest());

        // Validate the PenaltyFee in the database
        List<PenaltyFee> penaltyFeeList = penaltyFeeRepository.findAll();
        assertThat(penaltyFeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPenaltyFees() throws Exception {
        // Initialize the database
        penaltyFeeRepository.saveAndFlush(penaltyFee);

        // Get all the penaltyFeeList
        restPenaltyFeeMockMvc.perform(get("/api/penalty-fees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(penaltyFee.getId().intValue())))
            .andExpect(jsonPath("$.[*].concept").value(hasItem(DEFAULT_CONCEPT.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].registrationDate").value(hasItem(DEFAULT_REGISTRATION_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getPenaltyFee() throws Exception {
        // Initialize the database
        penaltyFeeRepository.saveAndFlush(penaltyFee);

        // Get the penaltyFee
        restPenaltyFeeMockMvc.perform(get("/api/penalty-fees/{id}", penaltyFee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(penaltyFee.getId().intValue()))
            .andExpect(jsonPath("$.concept").value(DEFAULT_CONCEPT.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.registrationDate").value(DEFAULT_REGISTRATION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPenaltyFee() throws Exception {
        // Get the penaltyFee
        restPenaltyFeeMockMvc.perform(get("/api/penalty-fees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePenaltyFee() throws Exception {
        // Initialize the database
        penaltyFeeRepository.saveAndFlush(penaltyFee);

        int databaseSizeBeforeUpdate = penaltyFeeRepository.findAll().size();

        // Update the penaltyFee
        PenaltyFee updatedPenaltyFee = penaltyFeeRepository.findById(penaltyFee.getId()).get();
        // Disconnect from session so that the updates on updatedPenaltyFee are not directly saved in db
        em.detach(updatedPenaltyFee);
        updatedPenaltyFee
            .concept(UPDATED_CONCEPT)
            .amount(UPDATED_AMOUNT)
            .registrationDate(UPDATED_REGISTRATION_DATE);

        restPenaltyFeeMockMvc.perform(put("/api/penalty-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPenaltyFee)))
            .andExpect(status().isOk());

        // Validate the PenaltyFee in the database
        List<PenaltyFee> penaltyFeeList = penaltyFeeRepository.findAll();
        assertThat(penaltyFeeList).hasSize(databaseSizeBeforeUpdate);
        PenaltyFee testPenaltyFee = penaltyFeeList.get(penaltyFeeList.size() - 1);
        assertThat(testPenaltyFee.getConcept()).isEqualTo(UPDATED_CONCEPT);
        assertThat(testPenaltyFee.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPenaltyFee.getRegistrationDate()).isEqualTo(UPDATED_REGISTRATION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPenaltyFee() throws Exception {
        int databaseSizeBeforeUpdate = penaltyFeeRepository.findAll().size();

        // Create the PenaltyFee

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPenaltyFeeMockMvc.perform(put("/api/penalty-fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(penaltyFee)))
            .andExpect(status().isBadRequest());

        // Validate the PenaltyFee in the database
        List<PenaltyFee> penaltyFeeList = penaltyFeeRepository.findAll();
        assertThat(penaltyFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePenaltyFee() throws Exception {
        // Initialize the database
        penaltyFeeRepository.saveAndFlush(penaltyFee);

        int databaseSizeBeforeDelete = penaltyFeeRepository.findAll().size();

        // Delete the penaltyFee
        restPenaltyFeeMockMvc.perform(delete("/api/penalty-fees/{id}", penaltyFee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PenaltyFee> penaltyFeeList = penaltyFeeRepository.findAll();
        assertThat(penaltyFeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PenaltyFee.class);
        PenaltyFee penaltyFee1 = new PenaltyFee();
        penaltyFee1.setId(1L);
        PenaltyFee penaltyFee2 = new PenaltyFee();
        penaltyFee2.setId(penaltyFee1.getId());
        assertThat(penaltyFee1).isEqualTo(penaltyFee2);
        penaltyFee2.setId(2L);
        assertThat(penaltyFee1).isNotEqualTo(penaltyFee2);
        penaltyFee1.setId(null);
        assertThat(penaltyFee1).isNotEqualTo(penaltyFee2);
    }
}

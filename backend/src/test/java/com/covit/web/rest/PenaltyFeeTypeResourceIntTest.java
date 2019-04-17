package com.covit.web.rest;

import com.covit.CovitApp;

import com.covit.domain.PenaltyFeeType;
import com.covit.repository.PenaltyFeeTypeRepository;
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
 * Test class for the PenaltyFeeTypeResource REST controller.
 *
 * @see PenaltyFeeTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CovitApp.class)
public class PenaltyFeeTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private PenaltyFeeTypeRepository penaltyFeeTypeRepository;

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

    private MockMvc restPenaltyFeeTypeMockMvc;

    private PenaltyFeeType penaltyFeeType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PenaltyFeeTypeResource penaltyFeeTypeResource = new PenaltyFeeTypeResource(penaltyFeeTypeRepository);
        this.restPenaltyFeeTypeMockMvc = MockMvcBuilders.standaloneSetup(penaltyFeeTypeResource)
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
    public static PenaltyFeeType createEntity(EntityManager em) {
        PenaltyFeeType penaltyFeeType = new PenaltyFeeType()
            .name(DEFAULT_NAME);
        return penaltyFeeType;
    }

    @Before
    public void initTest() {
        penaltyFeeType = createEntity(em);
    }

    @Test
    @Transactional
    public void createPenaltyFeeType() throws Exception {
        int databaseSizeBeforeCreate = penaltyFeeTypeRepository.findAll().size();

        // Create the PenaltyFeeType
        restPenaltyFeeTypeMockMvc.perform(post("/api/penalty-fee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(penaltyFeeType)))
            .andExpect(status().isCreated());

        // Validate the PenaltyFeeType in the database
        List<PenaltyFeeType> penaltyFeeTypeList = penaltyFeeTypeRepository.findAll();
        assertThat(penaltyFeeTypeList).hasSize(databaseSizeBeforeCreate + 1);
        PenaltyFeeType testPenaltyFeeType = penaltyFeeTypeList.get(penaltyFeeTypeList.size() - 1);
        assertThat(testPenaltyFeeType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createPenaltyFeeTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = penaltyFeeTypeRepository.findAll().size();

        // Create the PenaltyFeeType with an existing ID
        penaltyFeeType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPenaltyFeeTypeMockMvc.perform(post("/api/penalty-fee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(penaltyFeeType)))
            .andExpect(status().isBadRequest());

        // Validate the PenaltyFeeType in the database
        List<PenaltyFeeType> penaltyFeeTypeList = penaltyFeeTypeRepository.findAll();
        assertThat(penaltyFeeTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPenaltyFeeTypes() throws Exception {
        // Initialize the database
        penaltyFeeTypeRepository.saveAndFlush(penaltyFeeType);

        // Get all the penaltyFeeTypeList
        restPenaltyFeeTypeMockMvc.perform(get("/api/penalty-fee-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(penaltyFeeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getPenaltyFeeType() throws Exception {
        // Initialize the database
        penaltyFeeTypeRepository.saveAndFlush(penaltyFeeType);

        // Get the penaltyFeeType
        restPenaltyFeeTypeMockMvc.perform(get("/api/penalty-fee-types/{id}", penaltyFeeType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(penaltyFeeType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPenaltyFeeType() throws Exception {
        // Get the penaltyFeeType
        restPenaltyFeeTypeMockMvc.perform(get("/api/penalty-fee-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePenaltyFeeType() throws Exception {
        // Initialize the database
        penaltyFeeTypeRepository.saveAndFlush(penaltyFeeType);

        int databaseSizeBeforeUpdate = penaltyFeeTypeRepository.findAll().size();

        // Update the penaltyFeeType
        PenaltyFeeType updatedPenaltyFeeType = penaltyFeeTypeRepository.findById(penaltyFeeType.getId()).get();
        // Disconnect from session so that the updates on updatedPenaltyFeeType are not directly saved in db
        em.detach(updatedPenaltyFeeType);
        updatedPenaltyFeeType
            .name(UPDATED_NAME);

        restPenaltyFeeTypeMockMvc.perform(put("/api/penalty-fee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPenaltyFeeType)))
            .andExpect(status().isOk());

        // Validate the PenaltyFeeType in the database
        List<PenaltyFeeType> penaltyFeeTypeList = penaltyFeeTypeRepository.findAll();
        assertThat(penaltyFeeTypeList).hasSize(databaseSizeBeforeUpdate);
        PenaltyFeeType testPenaltyFeeType = penaltyFeeTypeList.get(penaltyFeeTypeList.size() - 1);
        assertThat(testPenaltyFeeType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPenaltyFeeType() throws Exception {
        int databaseSizeBeforeUpdate = penaltyFeeTypeRepository.findAll().size();

        // Create the PenaltyFeeType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPenaltyFeeTypeMockMvc.perform(put("/api/penalty-fee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(penaltyFeeType)))
            .andExpect(status().isBadRequest());

        // Validate the PenaltyFeeType in the database
        List<PenaltyFeeType> penaltyFeeTypeList = penaltyFeeTypeRepository.findAll();
        assertThat(penaltyFeeTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePenaltyFeeType() throws Exception {
        // Initialize the database
        penaltyFeeTypeRepository.saveAndFlush(penaltyFeeType);

        int databaseSizeBeforeDelete = penaltyFeeTypeRepository.findAll().size();

        // Delete the penaltyFeeType
        restPenaltyFeeTypeMockMvc.perform(delete("/api/penalty-fee-types/{id}", penaltyFeeType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PenaltyFeeType> penaltyFeeTypeList = penaltyFeeTypeRepository.findAll();
        assertThat(penaltyFeeTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PenaltyFeeType.class);
        PenaltyFeeType penaltyFeeType1 = new PenaltyFeeType();
        penaltyFeeType1.setId(1L);
        PenaltyFeeType penaltyFeeType2 = new PenaltyFeeType();
        penaltyFeeType2.setId(penaltyFeeType1.getId());
        assertThat(penaltyFeeType1).isEqualTo(penaltyFeeType2);
        penaltyFeeType2.setId(2L);
        assertThat(penaltyFeeType1).isNotEqualTo(penaltyFeeType2);
        penaltyFeeType1.setId(null);
        assertThat(penaltyFeeType1).isNotEqualTo(penaltyFeeType2);
    }
}

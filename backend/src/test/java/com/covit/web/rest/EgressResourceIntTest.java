package com.covit.web.rest;

import com.covit.CovitApp;

import com.covit.domain.Egress;
import com.covit.repository.EgressRepository;
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

import com.covit.domain.enumeration.EgressType;
import com.covit.domain.enumeration.CollectionType;
import com.covit.domain.enumeration.EgressStatus;
/**
 * Test class for the EgressResource REST controller.
 *
 * @see EgressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CovitApp.class)
public class EgressResourceIntTest {

    private static final EgressType DEFAULT_EGRESS_TYPE = EgressType.GASTO_ADMINISTRACION;
    private static final EgressType UPDATED_EGRESS_TYPE = EgressType.GASTOS_FINANCIERO;

    private static final String DEFAULT_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REGISTRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DOCUMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOCUMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_GLOSS = "AAAAAAAAAA";
    private static final String UPDATED_GLOSS = "BBBBBBBBBB";

    private static final String DEFAULT_CONCEPT = "AAAAAAAAAA";
    private static final String UPDATED_CONCEPT = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_CONCEPT = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CONCEPT = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final Integer DEFAULT_PURCHASE_FEE = 1;
    private static final Integer UPDATED_PURCHASE_FEE = 2;

    private static final Integer DEFAULT_DISTRIBUTION_FEE = 1;
    private static final Integer UPDATED_DISTRIBUTION_FEE = 2;

    private static final Integer DEFAULT_POSITION = 1;
    private static final Integer UPDATED_POSITION = 2;

    private static final String DEFAULT_RESP_REGISTER = "AAAAAAAAAA";
    private static final String UPDATED_RESP_REGISTER = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_DOC = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_DOC = "BBBBBBBBBB";

    private static final CollectionType DEFAULT_COLLECTION_TYPE = CollectionType.FONDO_RESERVA;
    private static final CollectionType UPDATED_COLLECTION_TYPE = CollectionType.DISTRIBUCION;

    private static final EgressStatus DEFAULT_EGRESS_STATUS = EgressStatus.ELIMINADO;
    private static final EgressStatus UPDATED_EGRESS_STATUS = EgressStatus.PENDIENTE;

    @Autowired
    private EgressRepository egressRepository;

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

    private MockMvc restEgressMockMvc;

    private Egress egress;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EgressResource egressResource = new EgressResource(egressRepository);
        this.restEgressMockMvc = MockMvcBuilders.standaloneSetup(egressResource)
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
    public static Egress createEntity(EntityManager em) {
        Egress egress = new Egress()
            .egressType(DEFAULT_EGRESS_TYPE)
            .document(DEFAULT_DOCUMENT)
            .registrationDate(DEFAULT_REGISTRATION_DATE)
            .documentDate(DEFAULT_DOCUMENT_DATE)
            .gloss(DEFAULT_GLOSS)
            .concept(DEFAULT_CONCEPT)
            .subConcept(DEFAULT_SUB_CONCEPT)
            .amount(DEFAULT_AMOUNT)
            .purchaseFee(DEFAULT_PURCHASE_FEE)
            .distributionFee(DEFAULT_DISTRIBUTION_FEE)
            .position(DEFAULT_POSITION)
            .respRegister(DEFAULT_RESP_REGISTER)
            .imageDoc(DEFAULT_IMAGE_DOC)
            .collectionType(DEFAULT_COLLECTION_TYPE)
            .egressStatus(DEFAULT_EGRESS_STATUS);
        return egress;
    }

    @Before
    public void initTest() {
        egress = createEntity(em);
    }

    @Test
    @Transactional
    public void createEgress() throws Exception {
        int databaseSizeBeforeCreate = egressRepository.findAll().size();

        // Create the Egress
        restEgressMockMvc.perform(post("/api/egresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(egress)))
            .andExpect(status().isCreated());

        // Validate the Egress in the database
        List<Egress> egressList = egressRepository.findAll();
        assertThat(egressList).hasSize(databaseSizeBeforeCreate + 1);
        Egress testEgress = egressList.get(egressList.size() - 1);
        assertThat(testEgress.getEgressType()).isEqualTo(DEFAULT_EGRESS_TYPE);
        assertThat(testEgress.getDocument()).isEqualTo(DEFAULT_DOCUMENT);
        assertThat(testEgress.getRegistrationDate()).isEqualTo(DEFAULT_REGISTRATION_DATE);
        assertThat(testEgress.getDocumentDate()).isEqualTo(DEFAULT_DOCUMENT_DATE);
        assertThat(testEgress.getGloss()).isEqualTo(DEFAULT_GLOSS);
        assertThat(testEgress.getConcept()).isEqualTo(DEFAULT_CONCEPT);
        assertThat(testEgress.getSubConcept()).isEqualTo(DEFAULT_SUB_CONCEPT);
        assertThat(testEgress.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testEgress.getPurchaseFee()).isEqualTo(DEFAULT_PURCHASE_FEE);
        assertThat(testEgress.getDistributionFee()).isEqualTo(DEFAULT_DISTRIBUTION_FEE);
        assertThat(testEgress.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testEgress.getRespRegister()).isEqualTo(DEFAULT_RESP_REGISTER);
        assertThat(testEgress.getImageDoc()).isEqualTo(DEFAULT_IMAGE_DOC);
        assertThat(testEgress.getCollectionType()).isEqualTo(DEFAULT_COLLECTION_TYPE);
        assertThat(testEgress.getEgressStatus()).isEqualTo(DEFAULT_EGRESS_STATUS);
    }

    @Test
    @Transactional
    public void createEgressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = egressRepository.findAll().size();

        // Create the Egress with an existing ID
        egress.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEgressMockMvc.perform(post("/api/egresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(egress)))
            .andExpect(status().isBadRequest());

        // Validate the Egress in the database
        List<Egress> egressList = egressRepository.findAll();
        assertThat(egressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEgresses() throws Exception {
        // Initialize the database
        egressRepository.saveAndFlush(egress);

        // Get all the egressList
        restEgressMockMvc.perform(get("/api/egresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(egress.getId().intValue())))
            .andExpect(jsonPath("$.[*].egressType").value(hasItem(DEFAULT_EGRESS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].document").value(hasItem(DEFAULT_DOCUMENT.toString())))
            .andExpect(jsonPath("$.[*].registrationDate").value(hasItem(DEFAULT_REGISTRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].documentDate").value(hasItem(DEFAULT_DOCUMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].gloss").value(hasItem(DEFAULT_GLOSS.toString())))
            .andExpect(jsonPath("$.[*].concept").value(hasItem(DEFAULT_CONCEPT.toString())))
            .andExpect(jsonPath("$.[*].subConcept").value(hasItem(DEFAULT_SUB_CONCEPT.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].purchaseFee").value(hasItem(DEFAULT_PURCHASE_FEE)))
            .andExpect(jsonPath("$.[*].distributionFee").value(hasItem(DEFAULT_DISTRIBUTION_FEE)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].respRegister").value(hasItem(DEFAULT_RESP_REGISTER.toString())))
            .andExpect(jsonPath("$.[*].imageDoc").value(hasItem(DEFAULT_IMAGE_DOC.toString())))
            .andExpect(jsonPath("$.[*].collectionType").value(hasItem(DEFAULT_COLLECTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].egressStatus").value(hasItem(DEFAULT_EGRESS_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getEgress() throws Exception {
        // Initialize the database
        egressRepository.saveAndFlush(egress);

        // Get the egress
        restEgressMockMvc.perform(get("/api/egresses/{id}", egress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(egress.getId().intValue()))
            .andExpect(jsonPath("$.egressType").value(DEFAULT_EGRESS_TYPE.toString()))
            .andExpect(jsonPath("$.document").value(DEFAULT_DOCUMENT.toString()))
            .andExpect(jsonPath("$.registrationDate").value(DEFAULT_REGISTRATION_DATE.toString()))
            .andExpect(jsonPath("$.documentDate").value(DEFAULT_DOCUMENT_DATE.toString()))
            .andExpect(jsonPath("$.gloss").value(DEFAULT_GLOSS.toString()))
            .andExpect(jsonPath("$.concept").value(DEFAULT_CONCEPT.toString()))
            .andExpect(jsonPath("$.subConcept").value(DEFAULT_SUB_CONCEPT.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.purchaseFee").value(DEFAULT_PURCHASE_FEE))
            .andExpect(jsonPath("$.distributionFee").value(DEFAULT_DISTRIBUTION_FEE))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.respRegister").value(DEFAULT_RESP_REGISTER.toString()))
            .andExpect(jsonPath("$.imageDoc").value(DEFAULT_IMAGE_DOC.toString()))
            .andExpect(jsonPath("$.collectionType").value(DEFAULT_COLLECTION_TYPE.toString()))
            .andExpect(jsonPath("$.egressStatus").value(DEFAULT_EGRESS_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEgress() throws Exception {
        // Get the egress
        restEgressMockMvc.perform(get("/api/egresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEgress() throws Exception {
        // Initialize the database
        egressRepository.saveAndFlush(egress);

        int databaseSizeBeforeUpdate = egressRepository.findAll().size();

        // Update the egress
        Egress updatedEgress = egressRepository.findById(egress.getId()).get();
        // Disconnect from session so that the updates on updatedEgress are not directly saved in db
        em.detach(updatedEgress);
        updatedEgress
            .egressType(UPDATED_EGRESS_TYPE)
            .document(UPDATED_DOCUMENT)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .documentDate(UPDATED_DOCUMENT_DATE)
            .gloss(UPDATED_GLOSS)
            .concept(UPDATED_CONCEPT)
            .subConcept(UPDATED_SUB_CONCEPT)
            .amount(UPDATED_AMOUNT)
            .purchaseFee(UPDATED_PURCHASE_FEE)
            .distributionFee(UPDATED_DISTRIBUTION_FEE)
            .position(UPDATED_POSITION)
            .respRegister(UPDATED_RESP_REGISTER)
            .imageDoc(UPDATED_IMAGE_DOC)
            .collectionType(UPDATED_COLLECTION_TYPE)
            .egressStatus(UPDATED_EGRESS_STATUS);

        restEgressMockMvc.perform(put("/api/egresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEgress)))
            .andExpect(status().isOk());

        // Validate the Egress in the database
        List<Egress> egressList = egressRepository.findAll();
        assertThat(egressList).hasSize(databaseSizeBeforeUpdate);
        Egress testEgress = egressList.get(egressList.size() - 1);
        assertThat(testEgress.getEgressType()).isEqualTo(UPDATED_EGRESS_TYPE);
        assertThat(testEgress.getDocument()).isEqualTo(UPDATED_DOCUMENT);
        assertThat(testEgress.getRegistrationDate()).isEqualTo(UPDATED_REGISTRATION_DATE);
        assertThat(testEgress.getDocumentDate()).isEqualTo(UPDATED_DOCUMENT_DATE);
        assertThat(testEgress.getGloss()).isEqualTo(UPDATED_GLOSS);
        assertThat(testEgress.getConcept()).isEqualTo(UPDATED_CONCEPT);
        assertThat(testEgress.getSubConcept()).isEqualTo(UPDATED_SUB_CONCEPT);
        assertThat(testEgress.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testEgress.getPurchaseFee()).isEqualTo(UPDATED_PURCHASE_FEE);
        assertThat(testEgress.getDistributionFee()).isEqualTo(UPDATED_DISTRIBUTION_FEE);
        assertThat(testEgress.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testEgress.getRespRegister()).isEqualTo(UPDATED_RESP_REGISTER);
        assertThat(testEgress.getImageDoc()).isEqualTo(UPDATED_IMAGE_DOC);
        assertThat(testEgress.getCollectionType()).isEqualTo(UPDATED_COLLECTION_TYPE);
        assertThat(testEgress.getEgressStatus()).isEqualTo(UPDATED_EGRESS_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingEgress() throws Exception {
        int databaseSizeBeforeUpdate = egressRepository.findAll().size();

        // Create the Egress

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEgressMockMvc.perform(put("/api/egresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(egress)))
            .andExpect(status().isBadRequest());

        // Validate the Egress in the database
        List<Egress> egressList = egressRepository.findAll();
        assertThat(egressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEgress() throws Exception {
        // Initialize the database
        egressRepository.saveAndFlush(egress);

        int databaseSizeBeforeDelete = egressRepository.findAll().size();

        // Delete the egress
        restEgressMockMvc.perform(delete("/api/egresses/{id}", egress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Egress> egressList = egressRepository.findAll();
        assertThat(egressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Egress.class);
        Egress egress1 = new Egress();
        egress1.setId(1L);
        Egress egress2 = new Egress();
        egress2.setId(egress1.getId());
        assertThat(egress1).isEqualTo(egress2);
        egress2.setId(2L);
        assertThat(egress1).isNotEqualTo(egress2);
        egress1.setId(null);
        assertThat(egress1).isNotEqualTo(egress2);
    }
}

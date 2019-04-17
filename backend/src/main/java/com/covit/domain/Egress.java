package com.covit.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.covit.domain.enumeration.EgressType;

import com.covit.domain.enumeration.CollectionType;

import com.covit.domain.enumeration.EgressStatus;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Entity
@Table(name = "egress")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Egress implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Enumerated(EnumType.STRING)
    @Column(name = "egress_type")
    private EgressType egressType;

    @Column(name = "document")
    private String document;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "document_date")
    private LocalDate documentDate;

    @Column(name = "gloss")
    private String gloss;

    @Column(name = "concept")
    private String concept;

    @Column(name = "sub_concept")
    private String subConcept;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "purchase_fee")
    private Integer purchaseFee;

    @Column(name = "distribution_fee")
    private Integer distributionFee;

    @Column(name = "position")
    private Integer position;

    @Column(name = "resp_register")
    private String respRegister;

    @Column(name = "image_doc")
    private String imageDoc;

    @Enumerated(EnumType.STRING)
    @Column(name = "collection_type")
    private CollectionType collectionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "egress_status")
    private EgressStatus egressStatus;

    @ManyToOne
    @JsonIgnoreProperties("egresses")
    private Provider provider;

    @ManyToOne
    @JsonIgnoreProperties("egresses")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("egresses")
    private Tower tower;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EgressType getEgressType() {
        return egressType;
    }

    public Egress egressType(EgressType egressType) {
        this.egressType = egressType;
        return this;
    }

    public void setEgressType(EgressType egressType) {
        this.egressType = egressType;
    }

    public String getDocument() {
        return document;
    }

    public Egress document(String document) {
        this.document = document;
        return this;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Egress registrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public Egress documentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
        return this;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    public String getGloss() {
        return gloss;
    }

    public Egress gloss(String gloss) {
        this.gloss = gloss;
        return this;
    }

    public void setGloss(String gloss) {
        this.gloss = gloss;
    }

    public String getConcept() {
        return concept;
    }

    public Egress concept(String concept) {
        this.concept = concept;
        return this;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getSubConcept() {
        return subConcept;
    }

    public Egress subConcept(String subConcept) {
        this.subConcept = subConcept;
        return this;
    }

    public void setSubConcept(String subConcept) {
        this.subConcept = subConcept;
    }

    public Double getAmount() {
        return amount;
    }

    public Egress amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPurchaseFee() {
        return purchaseFee;
    }

    public Egress purchaseFee(Integer purchaseFee) {
        this.purchaseFee = purchaseFee;
        return this;
    }

    public void setPurchaseFee(Integer purchaseFee) {
        this.purchaseFee = purchaseFee;
    }

    public Integer getDistributionFee() {
        return distributionFee;
    }

    public Egress distributionFee(Integer distributionFee) {
        this.distributionFee = distributionFee;
        return this;
    }

    public void setDistributionFee(Integer distributionFee) {
        this.distributionFee = distributionFee;
    }

    public Integer getPosition() {
        return position;
    }

    public Egress position(Integer position) {
        this.position = position;
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getRespRegister() {
        return respRegister;
    }

    public Egress respRegister(String respRegister) {
        this.respRegister = respRegister;
        return this;
    }

    public void setRespRegister(String respRegister) {
        this.respRegister = respRegister;
    }

    public String getImageDoc() {
        return imageDoc;
    }

    public Egress imageDoc(String imageDoc) {
        this.imageDoc = imageDoc;
        return this;
    }

    public void setImageDoc(String imageDoc) {
        this.imageDoc = imageDoc;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }

    public Egress collectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
        return this;
    }

    public void setCollectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
    }

    public EgressStatus getEgressStatus() {
        return egressStatus;
    }

    public Egress egressStatus(EgressStatus egressStatus) {
        this.egressStatus = egressStatus;
        return this;
    }

    public void setEgressStatus(EgressStatus egressStatus) {
        this.egressStatus = egressStatus;
    }

    public Provider getProvider() {
        return provider;
    }

    public Egress provider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Department getDepartment() {
        return department;
    }

    public Egress department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Tower getTower() {
        return tower;
    }

    public Egress tower(Tower tower) {
        this.tower = tower;
        return this;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Egress egress = (Egress) o;
        if (egress.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), egress.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Egress{" +
            "id=" + getId() +
            ", egressType='" + getEgressType() + "'" +
            ", document='" + getDocument() + "'" +
            ", registrationDate='" + getRegistrationDate() + "'" +
            ", documentDate='" + getDocumentDate() + "'" +
            ", gloss='" + getGloss() + "'" +
            ", concept='" + getConcept() + "'" +
            ", subConcept='" + getSubConcept() + "'" +
            ", amount=" + getAmount() +
            ", purchaseFee=" + getPurchaseFee() +
            ", distributionFee=" + getDistributionFee() +
            ", position=" + getPosition() +
            ", respRegister='" + getRespRegister() + "'" +
            ", imageDoc='" + getImageDoc() + "'" +
            ", collectionType='" + getCollectionType() + "'" +
            ", egressStatus='" + getEgressStatus() + "'" +
            "}";
    }
}

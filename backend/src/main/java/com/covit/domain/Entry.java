package com.covit.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.covit.domain.enumeration.EntryType;

import com.covit.domain.enumeration.WayToPay;

import com.covit.domain.enumeration.EntryStatus;

/**
 * Task entity.
 * @author The JHipster team.
 */
@ApiModel(description = "Task entity. @author The JHipster team.")
@Entity
@Table(name = "entry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Entry implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_type")
    private EntryType entryType;

    @Column(name = "description")
    private String description;

    @Column(name = "payment_number")
    private String paymentNumber;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "payer_name")
    private String payerName;

    @Column(name = "concept")
    private String concept;

    @Enumerated(EnumType.STRING)
    @Column(name = "way_to_pay")
    private WayToPay wayToPay;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "gloss")
    private String gloss;

    @Column(name = "comments")
    private String comments;

    @Column(name = "resp_register")
    private String respRegister;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EntryStatus status;

    @Column(name = "ref_document")
    private String refDocument;

    @ManyToOne
    @JsonIgnoreProperties("entries")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("entries")
    private PenaltyFee penaltyFee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Entry title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public Entry entryType(EntryType entryType) {
        this.entryType = entryType;
        return this;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }

    public String getDescription() {
        return description;
    }

    public Entry description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public Entry paymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
        return this;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public Entry receiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPayerName() {
        return payerName;
    }

    public Entry payerName(String payerName) {
        this.payerName = payerName;
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getConcept() {
        return concept;
    }

    public Entry concept(String concept) {
        this.concept = concept;
        return this;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public WayToPay getWayToPay() {
        return wayToPay;
    }

    public Entry wayToPay(WayToPay wayToPay) {
        this.wayToPay = wayToPay;
        return this;
    }

    public void setWayToPay(WayToPay wayToPay) {
        this.wayToPay = wayToPay;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public Entry paymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public Entry amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getGloss() {
        return gloss;
    }

    public Entry gloss(String gloss) {
        this.gloss = gloss;
        return this;
    }

    public void setGloss(String gloss) {
        this.gloss = gloss;
    }

    public String getComments() {
        return comments;
    }

    public Entry comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRespRegister() {
        return respRegister;
    }

    public Entry respRegister(String respRegister) {
        this.respRegister = respRegister;
        return this;
    }

    public void setRespRegister(String respRegister) {
        this.respRegister = respRegister;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Entry registrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public Entry status(EntryStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
    }

    public String getRefDocument() {
        return refDocument;
    }

    public Entry refDocument(String refDocument) {
        this.refDocument = refDocument;
        return this;
    }

    public void setRefDocument(String refDocument) {
        this.refDocument = refDocument;
    }

    public Department getDepartment() {
        return department;
    }

    public Entry department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public PenaltyFee getPenaltyFee() {
        return penaltyFee;
    }

    public Entry penaltyFee(PenaltyFee penaltyFee) {
        this.penaltyFee = penaltyFee;
        return this;
    }

    public void setPenaltyFee(PenaltyFee penaltyFee) {
        this.penaltyFee = penaltyFee;
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
        Entry entry = (Entry) o;
        if (entry.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entry.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Entry{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", entryType='" + getEntryType() + "'" +
            ", description='" + getDescription() + "'" +
            ", paymentNumber='" + getPaymentNumber() + "'" +
            ", receiptNumber='" + getReceiptNumber() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", concept='" + getConcept() + "'" +
            ", wayToPay='" + getWayToPay() + "'" +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", amount=" + getAmount() +
            ", gloss='" + getGloss() + "'" +
            ", comments='" + getComments() + "'" +
            ", respRegister='" + getRespRegister() + "'" +
            ", registrationDate='" + getRegistrationDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", refDocument='" + getRefDocument() + "'" +
            "}";
    }
}

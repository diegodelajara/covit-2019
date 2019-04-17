package com.covit.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A PenaltyFee.
 */
@Entity
@Table(name = "penalty_fee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PenaltyFee implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "concept")
    private String concept;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne
    @JsonIgnoreProperties("penaltyFees")
    private Department departament;

    @ManyToOne
    @JsonIgnoreProperties("penaltyFees")
    private PenaltyFeeType penaltyFeeType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public PenaltyFee concept(String concept) {
        this.concept = concept;
        return this;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Double getAmount() {
        return amount;
    }

    public PenaltyFee amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public PenaltyFee registrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Department getDepartament() {
        return departament;
    }

    public PenaltyFee departament(Department department) {
        this.departament = department;
        return this;
    }

    public void setDepartament(Department department) {
        this.departament = department;
    }

    public PenaltyFeeType getPenaltyFeeType() {
        return penaltyFeeType;
    }

    public PenaltyFee penaltyFeeType(PenaltyFeeType penaltyFeeType) {
        this.penaltyFeeType = penaltyFeeType;
        return this;
    }

    public void setPenaltyFeeType(PenaltyFeeType penaltyFeeType) {
        this.penaltyFeeType = penaltyFeeType;
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
        PenaltyFee penaltyFee = (PenaltyFee) o;
        if (penaltyFee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), penaltyFee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PenaltyFee{" +
            "id=" + getId() +
            ", concept='" + getConcept() + "'" +
            ", amount=" + getAmount() +
            ", registrationDate='" + getRegistrationDate() + "'" +
            "}";
    }
}

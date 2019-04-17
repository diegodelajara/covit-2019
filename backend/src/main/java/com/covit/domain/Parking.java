package com.covit.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Parking.
 */
@Entity
@Table(name = "parking")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Parking implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "used")
    private Boolean used;

    @Column(name = "patent")
    private String patent;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "hour")
    private LocalDate hour;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Parking name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isUsed() {
        return used;
    }

    public Parking used(Boolean used) {
        this.used = used;
        return this;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public String getPatent() {
        return patent;
    }

    public Parking patent(String patent) {
        this.patent = patent;
        return this;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public Parking arrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getHour() {
        return hour;
    }

    public Parking hour(LocalDate hour) {
        this.hour = hour;
        return this;
    }

    public void setHour(LocalDate hour) {
        this.hour = hour;
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
        Parking parking = (Parking) o;
        if (parking.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), parking.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Parking{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", used='" + isUsed() + "'" +
            ", patent='" + getPatent() + "'" +
            ", arrivalDate='" + getArrivalDate() + "'" +
            ", hour='" + getHour() + "'" +
            "}";
    }
}

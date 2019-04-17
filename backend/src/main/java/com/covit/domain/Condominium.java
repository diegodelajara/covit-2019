package com.covit.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Condominium.
 */
@Entity
@Table(name = "condominium")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Condominium implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "commune")
    private String commune;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "mail")
    private String mail;

    @Column(name = "number_one")
    private String numberOne;

    @Column(name = "number_two")
    private String numberTwo;

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

    public Condominium name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Condominium address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommune() {
        return commune;
    }

    public Condominium commune(String commune) {
        this.commune = commune;
        return this;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Condominium contactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getMail() {
        return mail;
    }

    public Condominium mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumberOne() {
        return numberOne;
    }

    public Condominium numberOne(String numberOne) {
        this.numberOne = numberOne;
        return this;
    }

    public void setNumberOne(String numberOne) {
        this.numberOne = numberOne;
    }

    public String getNumberTwo() {
        return numberTwo;
    }

    public Condominium numberTwo(String numberTwo) {
        this.numberTwo = numberTwo;
        return this;
    }

    public void setNumberTwo(String numberTwo) {
        this.numberTwo = numberTwo;
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
        Condominium condominium = (Condominium) o;
        if (condominium.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), condominium.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Condominium{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", commune='" + getCommune() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", mail='" + getMail() + "'" +
            ", numberOne='" + getNumberOne() + "'" +
            ", numberTwo='" + getNumberTwo() + "'" +
            "}";
    }
}

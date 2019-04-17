package com.covit.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "resident_rut")
    private String residentRut;

    @Column(name = "floor")
    private String floor;

    @OneToOne
    @JoinColumn(unique = true)
    private Parking parking;

    @OneToOne
    @JoinColumn(unique = true)
    private Owner owner;

    @OneToOne
    @JoinColumn(unique = true)
    private Cellar cellar;

    @OneToOne
    @JoinColumn(unique = true)
    private Tower tower;

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

    public Department name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public Department balance(Double balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDimensions() {
        return dimensions;
    }

    public Department dimensions(String dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getResidentRut() {
        return residentRut;
    }

    public Department residentRut(String residentRut) {
        this.residentRut = residentRut;
        return this;
    }

    public void setResidentRut(String residentRut) {
        this.residentRut = residentRut;
    }

    public String getFloor() {
        return floor;
    }

    public Department floor(String floor) {
        this.floor = floor;
        return this;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Parking getParking() {
        return parking;
    }

    public Department parking(Parking parking) {
        this.parking = parking;
        return this;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Owner getOwner() {
        return owner;
    }

    public Department owner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Cellar getCellar() {
        return cellar;
    }

    public Department cellar(Cellar cellar) {
        this.cellar = cellar;
        return this;
    }

    public void setCellar(Cellar cellar) {
        this.cellar = cellar;
    }

    public Tower getTower() {
        return tower;
    }

    public Department tower(Tower tower) {
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
        Department department = (Department) o;
        if (department.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), department.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", balance=" + getBalance() +
            ", dimensions='" + getDimensions() + "'" +
            ", residentRut='" + getResidentRut() + "'" +
            ", floor='" + getFloor() + "'" +
            "}";
    }
}

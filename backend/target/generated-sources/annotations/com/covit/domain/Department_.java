package com.covit.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static volatile SingularAttribute<Department, Parking> parking;
	public static volatile SingularAttribute<Department, Owner> owner;
	public static volatile SingularAttribute<Department, Cellar> cellar;
	public static volatile SingularAttribute<Department, Double> balance;
	public static volatile SingularAttribute<Department, String> residentRut;
	public static volatile SingularAttribute<Department, String> name;
	public static volatile SingularAttribute<Department, Long> id;
	public static volatile SingularAttribute<Department, String> floor;
	public static volatile SingularAttribute<Department, String> dimensions;
	public static volatile SingularAttribute<Department, Tower> tower;

}


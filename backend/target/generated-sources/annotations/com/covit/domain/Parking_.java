package com.covit.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parking.class)
public abstract class Parking_ {

	public static volatile SingularAttribute<Parking, String> patent;
	public static volatile SingularAttribute<Parking, LocalDate> hour;
	public static volatile SingularAttribute<Parking, String> name;
	public static volatile SingularAttribute<Parking, Long> id;
	public static volatile SingularAttribute<Parking, Boolean> used;
	public static volatile SingularAttribute<Parking, LocalDate> arrivalDate;

}


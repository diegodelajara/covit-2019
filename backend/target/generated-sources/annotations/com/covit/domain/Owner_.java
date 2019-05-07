package com.covit.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Owner.class)
public abstract class Owner_ {

	public static volatile SingularAttribute<Owner, String> rut;
	public static volatile SingularAttribute<Owner, String> lastName;
	public static volatile SingularAttribute<Owner, String> number;
	public static volatile SingularAttribute<Owner, String> comments;
	public static volatile SingularAttribute<Owner, String> mail;
	public static volatile SingularAttribute<Owner, String> name;
	public static volatile SingularAttribute<Owner, Long> id;
	public static volatile SingularAttribute<Owner, String> residence;
	public static volatile SingularAttribute<Owner, LocalDate> birthDate;

}


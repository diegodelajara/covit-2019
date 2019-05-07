package com.covit.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tower.class)
public abstract class Tower_ {

	public static volatile SingularAttribute<Tower, String> phoneNumber;
	public static volatile SingularAttribute<Tower, String> name;
	public static volatile SingularAttribute<Tower, Condominium> condominium;
	public static volatile SingularAttribute<Tower, Long> id;
	public static volatile SingularAttribute<Tower, String> email;

}


package com.covit.domain;

import com.covit.domain.enumeration.CollectionType;
import com.covit.domain.enumeration.EgressStatus;
import com.covit.domain.enumeration.EgressType;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Egress.class)
public abstract class Egress_ {

	public static volatile SingularAttribute<Egress, LocalDate> documentDate;
	public static volatile SingularAttribute<Egress, Double> amount;
	public static volatile SingularAttribute<Egress, String> document;
	public static volatile SingularAttribute<Egress, String> concept;
	public static volatile SingularAttribute<Egress, Integer> distributionFee;
	public static volatile SingularAttribute<Egress, String> respRegister;
	public static volatile SingularAttribute<Egress, CollectionType> collectionType;
	public static volatile SingularAttribute<Egress, EgressStatus> egressStatus;
	public static volatile SingularAttribute<Egress, String> gloss;
	public static volatile SingularAttribute<Egress, Provider> provider;
	public static volatile SingularAttribute<Egress, String> subConcept;
	public static volatile SingularAttribute<Egress, EgressType> egressType;
	public static volatile SingularAttribute<Egress, LocalDate> registrationDate;
	public static volatile SingularAttribute<Egress, Long> id;
	public static volatile SingularAttribute<Egress, Integer> purchaseFee;
	public static volatile SingularAttribute<Egress, Integer> position;
	public static volatile SingularAttribute<Egress, Department> department;
	public static volatile SingularAttribute<Egress, String> imageDoc;
	public static volatile SingularAttribute<Egress, Tower> tower;

}


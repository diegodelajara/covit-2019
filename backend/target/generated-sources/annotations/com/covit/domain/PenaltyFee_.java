package com.covit.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PenaltyFee.class)
public abstract class PenaltyFee_ {

	public static volatile SingularAttribute<PenaltyFee, PenaltyFeeType> penaltyFeeType;
	public static volatile SingularAttribute<PenaltyFee, Double> amount;
	public static volatile SingularAttribute<PenaltyFee, String> concept;
	public static volatile SingularAttribute<PenaltyFee, LocalDate> registrationDate;
	public static volatile SingularAttribute<PenaltyFee, Long> id;
	public static volatile SingularAttribute<PenaltyFee, Department> departament;

}


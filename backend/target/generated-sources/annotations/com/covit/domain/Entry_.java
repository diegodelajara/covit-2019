package com.covit.domain;

import com.covit.domain.enumeration.EntryStatus;
import com.covit.domain.enumeration.EntryType;
import com.covit.domain.enumeration.WayToPay;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entry.class)
public abstract class Entry_ {

	public static volatile SingularAttribute<Entry, EntryType> entryType;
	public static volatile SingularAttribute<Entry, Double> amount;
	public static volatile SingularAttribute<Entry, String> refDocument;
	public static volatile SingularAttribute<Entry, String> comments;
	public static volatile SingularAttribute<Entry, String> concept;
	public static volatile SingularAttribute<Entry, WayToPay> wayToPay;
	public static volatile SingularAttribute<Entry, String> respRegister;
	public static volatile SingularAttribute<Entry, String> description;
	public static volatile SingularAttribute<Entry, String> title;
	public static volatile SingularAttribute<Entry, String> gloss;
	public static volatile SingularAttribute<Entry, LocalDate> registrationDate;
	public static volatile SingularAttribute<Entry, PenaltyFee> penaltyFee;
	public static volatile SingularAttribute<Entry, String> payerName;
	public static volatile SingularAttribute<Entry, Long> id;
	public static volatile SingularAttribute<Entry, LocalDate> paymentDate;
	public static volatile SingularAttribute<Entry, Department> department;
	public static volatile SingularAttribute<Entry, String> paymentNumber;
	public static volatile SingularAttribute<Entry, String> receiptNumber;
	public static volatile SingularAttribute<Entry, EntryStatus> status;

}


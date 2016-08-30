package com.crmfollowup.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EtapaOportunidad.class)
public abstract class EtapaOportunidad_ {

	public static volatile SingularAttribute<EtapaOportunidad, Float> probabilidad;
	public static volatile SingularAttribute<EtapaOportunidad, Long> id;
	public static volatile SingularAttribute<EtapaOportunidad, Integer> orden;
	public static volatile SingularAttribute<EtapaOportunidad, String> nombre;
	public static volatile SetAttribute<EtapaOportunidad, Oportunidad> oportunidades;

}


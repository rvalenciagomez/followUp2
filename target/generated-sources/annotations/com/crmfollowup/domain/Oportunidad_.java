package com.crmfollowup.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Oportunidad.class)
public abstract class Oportunidad_ {

	public static volatile SingularAttribute<Oportunidad, EtapaOportunidad> etapaOportunidad;
	public static volatile SingularAttribute<Oportunidad, String> movil;
	public static volatile SingularAttribute<Oportunidad, String> notas;
	public static volatile SingularAttribute<Oportunidad, Float> probabilidad;
	public static volatile SingularAttribute<Oportunidad, String> accionSiguiente;
	public static volatile SingularAttribute<Oportunidad, User> cliente;
	public static volatile SingularAttribute<Oportunidad, Date> fechaCierre;
	public static volatile SingularAttribute<Oportunidad, Float> ingreso;
	public static volatile SingularAttribute<Oportunidad, String> asunto;
	public static volatile SingularAttribute<Oportunidad, Date> fechaAccion;
	public static volatile SingularAttribute<Oportunidad, String> name;
	public static volatile SingularAttribute<Oportunidad, Long> id;
	public static volatile SingularAttribute<Oportunidad, String> telefono;
	public static volatile SingularAttribute<Oportunidad, User> user;
	public static volatile SingularAttribute<Oportunidad, String> email;

}


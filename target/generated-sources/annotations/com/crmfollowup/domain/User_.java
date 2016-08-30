package com.crmfollowup.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> movil;
	public static volatile SingularAttribute<User, Boolean> esCliente;
	public static volatile SingularAttribute<User, String> titulo;
	public static volatile SetAttribute<User, Oportunidad> oportunidadesClientes;
	public static volatile SingularAttribute<User, String> nombre;
	public static volatile SingularAttribute<User, String> RFC;
	public static volatile SetAttribute<User, Authorities> authorities;
	public static volatile SingularAttribute<User, Boolean> esEmpresa;
	public static volatile SetAttribute<User, Oportunidad> oportunidades;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> direccionEntrega;
	public static volatile SetAttribute<User, User> empresas;
	public static volatile SingularAttribute<User, String> direccionFacturacion;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> telefono;
	public static volatile SingularAttribute<User, User> empresa;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Boolean> esProveedor;

}


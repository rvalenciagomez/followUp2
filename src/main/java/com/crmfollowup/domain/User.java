package com.crmfollowup.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User
{
  private Long id;
  private String nombre;
  private String email;
  private String password;
  private String telefono;
  private String movil;
  private String titulo;
  private String RFC;
  private String direccionEntrega;
  private String direccionFacturacion;
  private Boolean esCliente;
  private Boolean esProveedor;
  private Boolean esEmpresa;
  
  private User empresa;  
  
  private Set<User> empresas = new HashSet<>();
  
  private Set<Authorities> authorities = new HashSet<>();
  
  private Set<Oportunidad> oportunidades = new HashSet<>();
  
  private Set<Oportunidad> oportunidadesClientes = new HashSet<>();
  
  public User() 
  {
  }
  public User(User user) 
  {
    this.id= user.getId();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.authorities = user.getAuthorities();
    this.oportunidades = user.getOportunidades();
    this.oportunidadesClientes = user.getOportunidadesClientes();
  }
  
  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
    
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public String getTelefono() {
    return telefono;
  }
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
  public String getMovil() {
    return movil;
  }
  public void setMovil(String movil) {
    this.movil = movil;
  }
  public String getTitulo() {
    return titulo;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public String getRFC() {
    return RFC;
  }
  public void setRFC(String rFC) {
    RFC = rFC;
  }
  public String getDireccionEntrega() {
    return direccionEntrega;
  }
  public void setDireccionEntrega(String direccionEntrega) {
    this.direccionEntrega = direccionEntrega;
  }
  public String getDireccionFacturacion() {
    return direccionFacturacion;
  }
  public void setDireccionFacturacion(String direccionFacturacion) {
    this.direccionFacturacion = direccionFacturacion;
  }
  public Boolean getEsCliente() {
    return esCliente;
  }
  public void setEsCliente(Boolean esCliente) {
    this.esCliente = esCliente;
  }
  public Boolean getEsProveedor() {
    return esProveedor;
  }
  public void setEsProveedor(Boolean esProveedor) {
    this.esProveedor = esProveedor;
  }
  public Boolean getEsEmpresa() {
    return esEmpresa;
  }
  public void setEsEmpresa(Boolean esEmpresa) {
    this.esEmpresa = esEmpresa;
  }
  
//- - - - - - -- T E R M I N A     N U E V O S - ------ - - - - -
  
  
  @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="user")
  public Set<Authorities> getAuthorities() 
  {
    return authorities;
  }
  public void setAuthorities(Set<Authorities> authorities) 
  {
    this.authorities = authorities;
  }
  
  
  @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="user", orphanRemoval=true)
  public Set<Oportunidad> getOportunidades() {
    return oportunidades;
  }
  public void setOportunidades(Set<Oportunidad> courses) {
    this.oportunidades = courses;
  }
  
  @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="cliente", orphanRemoval=true)
  public Set<Oportunidad> getOportunidadesClientes() {
    return oportunidadesClientes;
  }
  public void setOportunidadesClientes(Set<Oportunidad> oportunidadesClientes) {
    this.oportunidadesClientes = oportunidadesClientes;
  }
  
  @ManyToOne
  public User getEmpresa() {
    return empresa;
  }
  public void setEmpresa(User empresa) {
    this.empresa = empresa;
  }
  
  @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="empresa", orphanRemoval=true)
  public Set<User> getEmpresas() {
    return empresas;
  }
  public void setEmpresas(Set<User> empresas) {
    this.empresas = empresas;
  }
  
  
  
    
  
}


package com.crmfollowup.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Oportunidad 
{
  private Long id;
  
  private String asunto;
  private Float ingreso;
  private Float probabilidad;
  
  private User user;
  
  private User cliente;
  
  private String email;
  private String telefono;
  private String movil;
//  @Column(name = "EMP_ID")
  private String accionSiguiente;
  
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")   
  private Date fechaAccion;
  
  @DateTimeFormat(pattern = "dd/MM/yyyy")  
  private Date fechaCierre;
  
  private String notas;
  
  private EtapaOportunidad etapaOportunidad;  


  public String getName() {
    return asunto;
  }

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.asunto = name;
  }

  @ManyToOne
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
  
  
  // - - - - - - -- N U E V O S - ------ - - - - - 
  
  @ManyToOne
  public User getCliente() {
    return cliente;
  }
  
  public void setCliente(User cliente) {
    this.cliente = cliente;
  }

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  public Float getIngreso() {
    return ingreso;
  }

  public void setIngreso(Float ingreso) {
    this.ingreso = ingreso;
  }

  public Float getProbabilidad() {
    return probabilidad;
  }

  public void setProbabilidad(Float probabilidad) {
    this.probabilidad = probabilidad;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  public String getAccionSiguiente() {
    return accionSiguiente;
  }

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  public void setAccionSiguiente(String accionSiguiente) {
    this.accionSiguiente = accionSiguiente;
  }
  

  public Date getFechaAccion() {
    return fechaAccion;
  }

  public void setFechaAccion(Date fechaAccion) {
    this.fechaAccion = fechaAccion;
  }


  public Date getFechaCierre() {
    return fechaCierre;
  }

  public void setFechaCierre(Date fechaCierre) {
    this.fechaCierre = fechaCierre;
  }

  public String getNotas() {
    return notas;
  }

  public void setNotas(String notas) {
    this.notas = notas;
  }

  
  // - - - - - - -- T E R M I N A    N U E V O S - ------ - - - - - 

  
  @ManyToOne
  public EtapaOportunidad getEtapaOportunidad() {
    return etapaOportunidad;
  }

  public void setEtapaOportunidad(EtapaOportunidad etapaOportunidad) {
    this.etapaOportunidad = etapaOportunidad;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Oportunidad other = (Oportunidad) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
 
  
  
}

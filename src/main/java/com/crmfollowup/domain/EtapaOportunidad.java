package com.crmfollowup.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class EtapaOportunidad 
{
  private Long id;
  private String nombre;
  private Integer orden;
  private Float probabilidad;
  
  private Set<Oportunidad> oportunidades = new HashSet<>();
  
  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public Integer getOrden() {
    return orden;
  }
  public void setOrden(Integer orden) {
    this.orden = orden;
  }
  public Float getProbabilidad() {
    return probabilidad;
  }
  public void setProbabilidad(Float probabilidad) {
    this.probabilidad = probabilidad;
  }
  
  @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="etapaOportunidad", orphanRemoval=true)
  public Set<Oportunidad> getOportunidades() {
    return oportunidades;
  }
  public void setOportunidades(Set<Oportunidad> oportunidades) {
    this.oportunidades = oportunidades;
  }

}

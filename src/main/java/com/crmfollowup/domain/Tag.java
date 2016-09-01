package com.crmfollowup.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tag 
{
  public int id;
  public String tagName;

  //getter and setter methods

  public Tag(int id, String tagName) 
  {
    this.id = id;
    this.tagName = tagName;
  }

  @Id
  @GeneratedValue
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }
  
  
}

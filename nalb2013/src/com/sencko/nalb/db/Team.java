package com.sencko.nalb.db;

import com.google.appengine.api.datastore.Entity;

public class Team extends DBEntity{
  
  private static final String ABBREVIATION = "abbreviation";
  private static final String SITE = "site";
  private static final String DESCRIPTION = "description";
  private static final String NAME = "name";
  private static final String TEAM = "Team";
  
  
  protected String name;
  protected String description;
  protected String site;
  protected String abbreviation;
  
  
  public String getName() {
    return name;
  }
  public Team setName(String name) {
    this.name = name;
    return this;
  }
  public String getDescription() {
    return description;
  }
  public Team setDescription(String description) {
    this.description = description;
    return this;
  }
  public String getSite() {
    return site;
  }
  public Team setSite(String site) {
    this.site = site;
    return this;
  }
  public String getAbbreviation() {
    return abbreviation;
  }
  public Team setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
    return this;
  }
  public Team(){
    super(new Entity(TEAM));
  }
  
  public Team(Entity teamEntity){
    super(teamEntity);
    name = (String) entity.getProperty(NAME);
    description = (String) entity.getProperty(DESCRIPTION);
    site = (String) entity.getProperty(SITE);
    abbreviation = (String) entity.getProperty(ABBREVIATION);
  }
  
  @Override
  protected Entity constructEntity() {
    entity.setProperty(NAME, name);
    entity.setProperty(DESCRIPTION, description);
    entity.setProperty(SITE, site);
    entity.setProperty(ABBREVIATION, abbreviation);
    return entity;
  }
  
  @Override
  public int hashCode() {
    return name.hashCode();
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Team){
      return equalsOrNull(name, ((Team) obj).name);
    }
    return false;
  }
  
  
  
  //byte[] image;
  
  

}


package com.sencko.nalb.db;

import com.google.appengine.api.datastore.Entity;

public class Team extends DBEntity {

  private static final String ABBREVIATION = "abbreviation";
  private static final String SITE = "site";
  private static final String DESCRIPTION = "description";
  private static final String NAME = "name";

  protected String name;
  protected String description;
  protected String site;
  protected String abbreviation;
  protected Image image;

  public Team() {
    super();
  }

  public Team( Entity teamEntity) {
    super(teamEntity);
    name = (String) getProperty(NAME);
    description = (String) getProperty(DESCRIPTION);
    site = (String) getProperty(SITE);
    abbreviation = (String) getProperty(ABBREVIATION);
    image = (Image) getProperty("image");
  }

  @Override
  protected Entity constructEntity() {
    setProperty(NAME, name);
    setProperty(DESCRIPTION, description);
    setProperty(SITE, site);
    setProperty(ABBREVIATION, abbreviation);
    setProperty("image", image);
    return entity;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Team) {
      return equalsOrNull(name, ((Team) obj).name);
    }
    return false;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public String getSite() {
    return site;
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  public Team setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
    return this;
  }

  public Team setDescription(String description) {
    this.description = description;
    return this;
  }

  public Team setName(String name) {
    this.name = name;
    return this;
  }

  public Team setSite(String site) {
    this.site = site;
    return this;
  }

  // byte[] image;

}

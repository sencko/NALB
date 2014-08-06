
package com.sencko.nalb.db;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

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

  public static Team getTeam(String teamname, String abbreviation) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Filter idFilter = new FilterPredicate(NAME, FilterOperator.EQUAL, teamname);
    Query q = new Query(Team.class.getSimpleName()).setFilter(idFilter);
    PreparedQuery pq = datastore.prepare(q);
    for (Entity en : pq.asIterable()) {
      return new Team(en);
    }
    Team team = new Team();
    team.setName(teamname);
    team.setAbbreviation(abbreviation);
    team.save();
    return team;
  }

  // byte[] image;

  
  @Override
  public String toString() {
    return String.format("%s %s %s %s", abbreviation, name, description, site);
  }

  public static List<Team> getTeams(Tournament tour) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Filter firstFilter = new FilterPredicate("tournament", FilterOperator.EQUAL, tour.getKey());
    
    Query q = new Query(RosterSpot.class.getSimpleName()).addProjection(new PropertyProjection("team", Key.class)).
        setFilter(firstFilter).setDistinct(true);
    
    PreparedQuery pq = datastore.prepare(q);
    List<Team> ret = new ArrayList<Team>();
    for (Entity en : pq.asIterable()) {
      try {
        ret.add( (Team) createObjectByKey( (Key) en.getProperty("team")));
      } catch (Exception e) {
        e.printStackTrace();
      } 
    }
    return ret;
  }
}

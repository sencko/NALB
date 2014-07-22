
package com.sencko.nalb.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Player extends DBEntity{

  private static final String PLAYER_TABLE = "Player";
  // private static final String IMAGE = "image";
  private static final String BIRTH_DATE = "birthDate";
  private static final String LAST_NAME = "lastName";
  private static final String MIDDLE_NAME = "middleName";
  private static final String FIRST_NAME = "firstName";
  public static final String ID = "id";

  protected Long id;
  protected String firstName;
  
  public Long getId() {
    return id;
  }

  public Player setId(Long id) {
    this.id = id;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Player setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getMiddleName() {
    return middleName;
  }

  public Player setMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Player setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public Date getDate() {
    return date;
  }

  public Player setDate(Date date) {
    this.date = date;
    return this;
  }

  protected String middleName;
  protected String lastName;
  protected Date date;
  protected byte[] image;

  public Player() {
    super(new Entity(PLAYER_TABLE));
  }

  public Player( Entity en) {
    super(en);
    id = (Long) en.getProperty(ID);
    firstName = (String) en.getProperty(FIRST_NAME);
    middleName = (String) en.getProperty(MIDDLE_NAME);
    lastName = (String) en.getProperty(LAST_NAME);
    date = (Date) en.getProperty(BIRTH_DATE);
    // image = ((Blob) en.getProperty(IMAGE)).getBytes();
  }
  
  public Key getKey(){
    return entity.getKey();
  }

  @Override
  public boolean equals(Object ob) {
    if (ob instanceof Player) {
      Player other = (Player) ob;
      return equalsOrNull(id, other.id) && equalsOrNull(firstName, other.firstName) && equalsOrNull(middleName, other.middleName)
          && equalsOrNull(lastName, other.lastName) && equalsOrNull(date, other.date);
    }
    return false;
  }
  
  

  @Override
  public int hashCode() {
    if (id != null){
      return id.hashCode();
    } else {
      return super.hashCode();
    }
  }

  public static Player getPlayer(long key) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Filter idFilter = new FilterPredicate(ID, FilterOperator.EQUAL, key);
    Query q = new Query(PLAYER_TABLE).setFilter(idFilter);
    PreparedQuery pq = datastore.prepare(q);
    for (Entity en : pq.asIterable()) {
      return new Player(en);
    }
    return null;
  }

  public static List<Player> searchForPlayerExact(String firstName, String lastName) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Filter firstFilter = new FilterPredicate(FIRST_NAME, FilterOperator.EQUAL, firstName);
    Filter thirdFilter = new FilterPredicate(LAST_NAME, FilterOperator.EQUAL, lastName);
    Filter composite = CompositeFilterOperator.and(firstFilter, thirdFilter);
    Query q = new Query(PLAYER_TABLE).setFilter(composite);
    PreparedQuery pq = datastore.prepare(q);
    List<Player> ret = new ArrayList<Player>();
    for (Entity en : pq.asIterable()) {
      ret.add(new Player(en));
    }
    return ret;
  }
  
 @Override
  public Entity constructEntity() {
    
    entity.setProperty(ID, id);
    entity.setProperty(FIRST_NAME, firstName);
    entity.setProperty(MIDDLE_NAME, middleName);
    entity.setProperty(LAST_NAME, lastName);
    entity.setProperty(BIRTH_DATE, date);
    // playerEntity.setProperty(IMAGE2, new Blob(image));
    return entity;
  }

}

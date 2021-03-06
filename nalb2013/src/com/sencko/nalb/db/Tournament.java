
package com.sencko.nalb.db;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Tournament extends DBEntity {
  protected String name;

  protected String description;

  protected Tournament parent;

  protected String schema;
  
  protected Image image;

  public Tournament() {
    super();
  }

  public Tournament( Entity tourEntity) {
    super(tourEntity);
    name = (String) getProperty("name");
    description = (String) getProperty("description");
    schema = (String) getProperty("schema");
    parent = (Tournament) getProperty("parent");
    image = (Image) getProperty("image");
  }

  @Override
  protected Entity constructEntity() {
    setProperty("name", name);
    setProperty("description", description);
    setProperty("schema", schema);
    setProperty("parent", parent);
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
    if (obj instanceof Tournament) {
      Tournament other = (Tournament) obj;
      return equalsOrNull(name, other.name) && equalsOrNull(description, other.description) && equalsOrNull(schema, other.schema)
          && equalsOrNull(parent, other.parent);
    }
    return false;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public Tournament getParent() {
    return parent;
  }

  public String getSchema() {
    return schema;
  }

  @Override
  public int hashCode() {
    return hashOrNull(name) ^ hashOrNull(description) ^ hashOrNull(schema) ^ hashOrNull(parent);
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setParent(Tournament parent) {
    this.parent = parent;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  
  @Override
  public String toString() {
    return String.format("%s %s (%s) %s", name, description, schema, parent);
  }

  public static List<Tournament> getTournaments() {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query q = new Query(Tournament.class.getSimpleName());
    PreparedQuery pq = datastore.prepare(q);
    List<Tournament>  ret = new ArrayList<Tournament>();
    for (Entity en : pq.asIterable()) {
      ret.add(new Tournament(en));
    }
    return ret;
  }
}


package com.sencko.nalb.db;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class Tournament extends DBEntity {
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Tournament getParent() {
    return parent;
  }

  public void setParent(Tournament parent) {
    this.parent = parent;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  @Override
  protected Entity constructEntity() {
    entity.setProperty("name", name);
    entity.setProperty("description", description);
    entity.setProperty("schema", schema);
    if (parent != null) {
      entity.setProperty("parent", parent.getKey());
    }
    return entity;
  }

  @Override
  public int hashCode() {
    return hashOrNull(name) ^ hashOrNull(description) ^ hashOrNull(schema) ^ hashOrNull(parent);
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

  protected String name;
  protected String description;
  // protected byte[] image;
  protected Tournament parent;
  protected String schema;

  public Tournament( Entity tourEntity) {
    super(tourEntity);
    name = (String) entity.getProperty("name");
    description = (String) entity.getProperty("description");
    schema = (String) entity.getProperty("schema");
    Key key = (Key) entity.getProperty("parent");
    if (key != null) {
      try {
        parent = new Tournament(DatastoreServiceFactory.getDatastoreService().get(key));
      } catch (EntityNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public Tournament() {
    super(new Entity("Tournament"));
  }

}

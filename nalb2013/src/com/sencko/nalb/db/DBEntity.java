package com.sencko.nalb.db;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public abstract class DBEntity {
  
  public DBEntity(Entity entity){
    this.entity = entity;
  }
  
  protected Entity entity;
  
  public Key getKey(){
    if (entity == null){
      save();
    }
    return entity.getKey();
  }
  
  protected void save(){
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put( constructEntity());
  }
  
  protected abstract Entity constructEntity();
  
  public static boolean equalsOrNull(Object one, Object two) {
    if (one == null) {
      if (two == null) {
        return true;
      } else {
        return false;
      }
    } else {
      return one.equals(two);
    }
  }
  
  public static int hashOrNull(Object one){
    if (one == null){
      return 0;
    } else {
      return one.hashCode();
    }
  }

}

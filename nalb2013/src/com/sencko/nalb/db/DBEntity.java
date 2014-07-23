
package com.sencko.nalb.db;

import java.lang.reflect.InvocationTargetException;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public abstract class DBEntity {

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

  public static int hashOrNull(Object one) {
    if (one == null) {
      return 0;
    } else {
      return one.hashCode();
    }
  }

  protected Entity entity;

  public DBEntity( Entity entity) {
    this.entity = entity;
  }

  public DBEntity() {
    this.entity = new Entity(getClass().getSimpleName());
  }

  protected abstract Entity constructEntity();

  public byte[] getBinaryProperty(String name) {
    Blob blob = (Blob) entity.getProperty(name);
    if (blob != null) {
      return blob.getBytes();
    }
    return null;
  }

  public Key getKey() {
    if (entity == null) {
      save();
    }
    return entity.getKey();
  }

  protected void save() {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(constructEntity());
  }

  public void setProperty(String key, Object value) {
    if (value instanceof DBEntity) {
      entity.setProperty(key, ((DBEntity) value).getKey());
    } else if (value instanceof byte[]) {
      Blob blob = new Blob((byte[]) value);
      entity.setProperty(key, blob);
    } else {
      entity.setProperty(key, value);
    }
  }

  public Object getProperty(String key) {
    Object object = entity.getProperty(key);
    if (object instanceof Key) {
      try {
        Entity entity = DatastoreServiceFactory.getDatastoreService().get((Key) object);
        String kind = entity.getKind();
        return Class.forName(DBEntity.class.getPackage().getName() + "." + kind).getConstructor(Entity.class).newInstance(entity);
      } catch (Exception ex) {
        ex.printStackTrace();
        object = null;
      }
    } else if (object instanceof Blob) {
      return ((Blob) object).getBytes();
    }
    return object;
  }

  public static void main(String[] test) throws Exception {
    LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    helper.setUp();
    new Game();
    helper.tearDown();
  }
}

/**
 * Copyright (c) 2014 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP AG, Walldorf. You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with SAP.
 * 
 * Created on Jul 23, 2014 by I028512
 * 
 */

package com.sencko.nalb.db;

import java.util.Arrays;

import com.google.appengine.api.datastore.Entity;

public class Image extends DBEntity {

  byte[] data;

  String description;

  String name;

  public Image() {
    super();
  }

  public Image( Entity imageEntity) {
    super(imageEntity);
    description = (String) getProperty("description");
    name = (String) getProperty("name");
    data = (byte[]) getProperty("data");
  }

  @Override
  protected Entity constructEntity() {
    setProperty("description", description);
    setProperty("name", name);
    setProperty("data", data);
    return entity;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Image) {
      Image other = (Image) obj;
      return Arrays.equals(data, other.data) && equalsOrNull(description, other.description) && equalsOrNull(name, other.name);
    }
    return false;
  }

  public byte[] getData() {
    return data;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(data) ^ hashOrNull(description) ^ hashOrNull(name);
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setName(String name) {
    this.name = name;
  }

}

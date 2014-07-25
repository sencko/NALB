
package com.sencko.nalb.db;

import com.google.appengine.api.datastore.Entity;

public class Alias extends DBEntity {

  protected String aliasKey;
  protected String aliasValue;
  protected Tournament tournament;

  public Alias() {
    super();
  }

  public Alias( Entity aliasEntity) {
    super(aliasEntity);
    aliasKey = (String) getProperty("aliasKey");
    aliasValue = (String) getProperty("aliasValue");
    tournament = (Tournament) getProperty("tournament");
  }

  public String getAliasKey() {
    return aliasKey;
  }

  public void setAliasKey(String aliasKey) {
    this.aliasKey = aliasKey;
  }

  public String getAliasValue() {
    return aliasValue;
  }

  public void setAliasValue(String aliasValue) {
    this.aliasValue = aliasValue;
  }

  public Tournament getTournament() {
    return tournament;
  }

  public void setTournament(Tournament tournament) {
    this.tournament = tournament;
  }

  @Override
  public int hashCode() {
    return hashOrNull(aliasKey) ^ hashOrNull(aliasValue)^hashOrNull(tournament);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Alias){
      Alias other = (Alias) obj;
      return equalsOrNull(aliasKey, other.aliasKey) && equalsOrNull(aliasValue, other.aliasValue) && equalsOrNull(tournament,  other.tournament);
    }
    return false;
  }

  @Override
  protected Entity constructEntity() {
    setProperty("aliasKey", aliasKey);
    setProperty("aliasValue", aliasValue);
    setProperty("tournament", tournament);
    return entity;
  }

}

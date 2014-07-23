
package org.sencko.nalb.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Player {

  String realName;
  Date bd;
  int id;
  Team team;
  
  List<PlayerStats> playerStats= new ArrayList<PlayerStats>();

  public Player( Team team2, String value, String[] pValue) {
    team = team2;
    

    realName = value;
    String[] date = pValue[0].split("\\.");
    bd = new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]) - 1);
    if (pValue.length > 1){
    id = Integer.parseInt(pValue[1].trim());
    } else {
    	id = realName.hashCode() ^ bd.hashCode();
    }
  }

  public String getName() {
    return realName;
  }

  public void add(PlayerStats playerStats2) {
   playerStats.add(playerStats2);
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Player){
      return ((Player) obj).id == id;
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("%s(%d)", realName, id);
  }
  
  public AveragePlayerStats getAveragePlayerStats(){
    return new AveragePlayerStats(this, playerStats);
  }

}

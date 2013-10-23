
package org.sencko.nalb.parser;

import java.util.Date;

public class Player {

  String realName;
  Date bd;
  String id;
  Team team;

  public Player( Team team2, String value, String[] pValue) {
    team = team2;
    

    realName = value;
    String[] date = pValue[0].split("\\.");
    bd = new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]) - 1);
    id = pValue[1];
  }

  public String getName() {
    return realName;
  }

}

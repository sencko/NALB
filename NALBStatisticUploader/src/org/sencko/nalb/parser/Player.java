
package org.sencko.nalb.parser;

import java.util.Date;

public class Player {

  String realName;
  String alias;
  Date bd;
  String id;
  Team team;

  public Player( Team team2, String value, String[] pValue) {
    team = team2;
    alias = value;

    realName = pValue[0];
    String[] date = pValue[1].split("\\.");
    bd = new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]) - 1);
    id = pValue[2];
  }

  public String getName() {
    return realName;
  }

}

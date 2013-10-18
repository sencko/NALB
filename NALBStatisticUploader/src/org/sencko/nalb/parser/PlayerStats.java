/**
 * Copyright (c) 2013 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP AG, Walldorf. You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with SAP.
 * 
 * Created on Oct 9, 2013 by I028512
 * 
 */

package org.sencko.nalb.parser;

import java.util.regex.Matcher;

public class PlayerStats {
  String team;

  public PlayerStats( Matcher head, String team) {
    this.team = team;
    starting = head.group(1) != null;
    number = Integer.parseInt(head.group(2));
    name = head.group(3);
    playTime = Integer.parseInt(head.group(4)) * 60 + Integer.parseInt(head.group(5));
    twoPoints = new ShootingStats(Integer.parseInt(head.group(9)), Integer.parseInt(head.group(10)));
    threePoints = new ShootingStats(Integer.parseInt(head.group(12)), Integer.parseInt(head.group(13)));
    freeThrow = new ShootingStats(Integer.parseInt(head.group(15)), Integer.parseInt(head.group(16)));
    offensiveRebounds = Integer.parseInt(head.group(18));
    defensiveRebounds = Integer.parseInt(head.group(19));
    assists = Integer.parseInt(head.group(21));
    turnovers = Integer.parseInt(head.group(22));
    steals = Integer.parseInt(head.group(23));
    blocks = Integer.parseInt(head.group(24));
    blocksAgainst = Integer.parseInt(head.group(25));
    fouls = Integer.parseInt(head.group(26));
    foulsAgainst = Integer.parseInt(head.group(27));
  }

  protected PlayerStats( String name) {
    this.name = name;
  }

  protected void add(PlayerStats player) {
    this.playTime += player.playTime;
    if (twoPoints == null) {
      twoPoints = new ShootingStats(0, 0);
      threePoints = new ShootingStats(0, 0);
      freeThrow = new ShootingStats(0, 0);
    }
    twoPoints.add(player.twoPoints);
    threePoints.add(player.threePoints);
    freeThrow.add(player.freeThrow);
    offensiveRebounds += player.offensiveRebounds;
    defensiveRebounds += player.defensiveRebounds;
    assists += player.assists;
    turnovers += player.turnovers;
    steals += player.steals;
    blocks += player.blocks;
    blocksAgainst += player.blocksAgainst;
    fouls += player.fouls;
    foulsAgainst += player.foulsAgainst;
  }

  boolean starting;
  int number;
  String name;
  int playTime;
  ShootingStats twoPoints, threePoints, freeThrow;
  int offensiveRebounds;
  int defensiveRebounds;
  int assists;
  int turnovers;
  int steals;
  int blocks;
  int blocksAgainst;
  int fouls;
  int foulsAgainst;

  @Override
  public String toString() {
    return name + "\n";
    // return String.format("%2d %30s %4d %s %s %s %2d %2d %2d %2d %2d %2d %2d %2d %2d %3d\n", number, name, playTime, twoPoints, threePoints,
    // freeThrow, offensiveRebounds, defensiveRebounds, assists, turnovers, steals, blocks, blocksAgainst, fouls, foulsAgainst, getPoints());
  }

  static String FORMAT = Util.readResource("player.html");

  public String toHTML() {
    return String.format(FORMAT, (starting ? "class=\"starter\"" : "class=\"sub\""), number, getName(), playTime / 60, playTime % 60, twoPoints.made,
        twoPoints.attempted, twoPoints.getPercentage(), threePoints.made, threePoints.attempted, threePoints.getPercentage(), freeThrow.made,
        freeThrow.attempted, freeThrow.getPercentage(), offensiveRebounds, defensiveRebounds, offensiveRebounds + defensiveRebounds, assists,
        turnovers, steals, blocks, blocksAgainst, fouls, foulsAgainst, getPoints(), getEfficiency()

    );
  }

  private String getName() {
    if (team == null) {
      return name;
    }
    
    String value = Util.getProperties(team).getProperty(Util.resolveDLA(Util.getProperties(team), name));// Util.getProperties(Util.resolveAlias(team)).getProperty(Util.resolveAlias(name));
    if (value == null) {
      System.out.println(team + ":" + name);
      return name;
    } else {
      return value.split("=")[0];
    }
  }

  int getPoints() {
    return 2 * twoPoints.made + 3 * threePoints.made + 1 * freeThrow.made;
  }

  int getEfficiency() {
    return getPoints() + offensiveRebounds + defensiveRebounds + assists + steals + blocks + foulsAgainst + twoPoints.made + threePoints.made
        - twoPoints.attempted - threePoints.attempted - turnovers - blocksAgainst - fouls;
  }

}


package org.sencko.nalb.parser;

import java.util.HashMap;
import java.util.Properties;

public class Team {
  static HashMap<String, Team> teamCache = new HashMap<String, Team>();
  private String alias;
  private String name;
  Properties players;
  HashMap<String, Player> playerCache = new HashMap<String, Player>();

  private Team( String alias2, Properties properties) {
    alias = alias2;
    players = properties;
    name = players.getProperty("name");
  }

  public static Team getTeam(String alias) {
    Team toRet = teamCache.get(alias);
    if (toRet == null) {
      String realAlias = Util.getTeamInitial(alias);
      toRet = teamCache.get(realAlias);
      if (toRet == null) {
        toRet = new Team(realAlias, Util.getProperties(realAlias));
        teamCache.put(realAlias, toRet);
      }
      if (!alias.equals(realAlias)) {
        teamCache.put(alias, toRet);
      }
    }
    return toRet;
  }

  public Player resolvePlayer(String name2) {
    Player toRet = playerCache.get(name2);
    if (toRet == null) {
      String name = Util.getProperties("aliases").getProperty(name2);
      if (name == null){
        name = name2;
      }
      String value = Util.dla.getClosestTransliterate(name, players.stringPropertyNames());//Util.resolveDLA(players, name2);// Util.getProperties(Util.resolveAlias(team)).getProperty(Util.resolveAlias(name));
      toRet = playerCache.get(value);
      if (toRet == null) {
        String pValue = players.getProperty(value);
        toRet = new Player(this, value, pValue.split("="));
        playerCache.put(value, toRet);
      }
      playerCache.put(name2, toRet);
    }
    return toRet;

  }

  public String getName() {
    return name;
  }

  public String getAlias() {
    return alias;
  }
}

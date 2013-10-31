
package org.sencko.nalb.parser;

import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Team {
  static HashMap<String, Team> teamCache = new HashMap<String, Team>();
  public static Set<Team> teamSet = new HashSet<Team>(); 
  private String alias;
  private String name;
  Properties players;
  HashMap<String, Player> playerCache = new HashMap<String, Player>();
  Set<Player> playerSet = new HashSet<Player>();

  private Team( String alias2, Properties properties) {
    alias = alias2;
    players = properties;
    name = players.getProperty("name");
  }
  
  public Team() {
    alias = "ALL";
    name = "Всички играчи";
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
    teamSet.add(toRet);
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
    playerSet.add(toRet);
    return toRet;

  }

  public String getName() {
    return name;
  }

  public String getAlias() {
    return alias;
  }
  
  static String TEAM_STATS = Util.readResource("team.html");
  
  public void toHtml(Formatter formatter) {
    StringBuilder builder = new StringBuilder();
    Formatter temp = new Formatter(builder);
    
    for(Player player:playerSet){
      AveragePlayerStats aps = player.getAveragePlayerStats();
      aps.toHTML(temp);
    }
    temp.close();
    String stringPlayers =  builder.toString();
    
    formatter.format(TEAM_STATS, name, name, name, stringPlayers, "");
  }
  
  public void add(Team team){
    playerSet.addAll(team.playerSet);
  }
  
}

package org.sencko.nalb.parser;

import java.util.ArrayList;
import java.util.Formatter;

public class TeamStats extends PlayerStats {
  static String TEAM_TOTAL = Util.readResource("total.html");

  Team team;
 
  
  private ArrayList<PlayerStats> playerStats = new ArrayList<PlayerStats>();
  private TeamTotals teamTotals;

  public TeamStats(String name1) {
    super(name1);
    team = Team.getTeam(name1);
  }

  public ArrayList<PlayerStats> getPlayerStats() {
    return playerStats;
  }
  
  public void toHTML(Formatter formatter) {
      formatter.format(TEAM_TOTAL,  team.getName(), twoPoints.made,
          twoPoints.attempted, twoPoints.getPercentage(), threePoints.made, threePoints.attempted, threePoints.getPercentage(), freeThrow.made,
          freeThrow.attempted, freeThrow.getPercentage(), offensiveRebounds, defensiveRebounds, offensiveRebounds + defensiveRebounds, assists,
          turnovers, steals, blocks, blocksAgainst, fouls, foulsAgainst, getPoints(), getEfficiency()

      );      

  }
  
  public void addPlayerStats(PlayerStats ps){
    getPlayerStats().add(ps);
    add(ps);
  }
  
  public Team getTeam(){
    return team;
  }

  public Player resolvePlayer(String name) {
    return team.resolvePlayer(name);
  }

  public String getAlias() {
    return name;
  }
  
  public void addTeamTotals(TeamTotals teamTotals) {
    this.teamTotals = teamTotals;
    add(teamTotals);
  }

  public TeamTotals getTeamTotals() {
    return teamTotals;
  }

}

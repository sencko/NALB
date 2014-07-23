
package com.sencko.nalb.db;

import org.sencko.nalb.parser.ShootingStats;

import com.google.appengine.api.datastore.Entity;

public class PlayerStatistic extends DBEntity {

  Player player;
  Team team;
  Game game;
  boolean isStarter;
  boolean isCaptain;
  boolean isCoach;
  int number;
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

  public PlayerStatistic() {
    super();
  }

  public PlayerStatistic( Entity playerEntity) {
    super(playerEntity);
    player = (Player) getProperty( "player");
    team = (Team) getProperty( "team");
    game = (Game) getProperty("game");
    isStarter = (Boolean) getProperty("isStarter");
    isCaptain = (Boolean) getProperty("isCaptain");
    isCoach = (Boolean) getProperty("isCoach");
    number = (Integer) getProperty("number");
    playTime = (Integer) getProperty("playTime");
    offensiveRebounds = (Integer) getProperty("offensiveRebounds");
    defensiveRebounds = (Integer) getProperty("defensiveRebounds");
    assists = (Integer) getProperty("assists");
    turnovers = (Integer) getProperty("turnovers");
    steals = (Integer) getProperty("steals");
    blocks = (Integer) getProperty("blocks");
    blocksAgainst = (Integer) getProperty("blocksAgainst");
    fouls = (Integer) getProperty("fouls");
    foulsAgainst = (Integer) getProperty("foulsAgainst");
    twoPoints = new ShootingStats((Integer) getProperty("twoPoints.made"), (Integer) getProperty("twoPoints.attempted"));
    threePoints = new ShootingStats((Integer) getProperty("threePoints.made"), (Integer) getProperty("threePoints.attempted"));
    freeThrow = new ShootingStats((Integer) getProperty("freeThrow.made"), (Integer) getProperty("freeThrow.attempted"));

  }

  @Override
  protected Entity constructEntity() {
    setProperty("player", player);
    setProperty("team", team);
    setProperty("game", game);
    setProperty("isStarter", isStarter);
    setProperty("isCaptain", isCaptain);
    setProperty("isCoach", isCoach);
    setProperty("number", number);
    setProperty("playTime", playTime);
    setProperty("offensiveRebounds", offensiveRebounds);
    setProperty("defensiveRebounds", defensiveRebounds);
    setProperty("assists", assists);
    setProperty("turnovers", turnovers);
    setProperty("steals", steals);
    setProperty("blocks", blocks);
    setProperty("blocksAgainst", blocksAgainst);
    setProperty("fouls", fouls);
    setProperty("foulsAgainst", foulsAgainst);
    setProperty("twoPoints.made", twoPoints.getMade());
    setProperty("twoPoints.attempted", twoPoints.getAttempted());
    setProperty("threePoints.made", threePoints.getMade());
    setProperty("threePoints.attempted", threePoints.getAttempted());
    setProperty("freeThrow.made", freeThrow.getMade());
    setProperty("freeThrow.attempted", freeThrow.getAttempted());
    return entity;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof PlayerStatistic) {
      PlayerStatistic other = (PlayerStatistic) obj;
      return equalsOrNull(player, other.player) && equalsOrNull(game, other.game) && equalsOrNull(team, other.team);
    }
    return false;
  }

  public int getAssists() {
    return assists;
  }

  public int getBlocks() {
    return blocks;
  }

  public int getBlocksAgainst() {
    return blocksAgainst;
  }

  public int getDefensiveRebounds() {
    return defensiveRebounds;
  }

  public int getFouls() {
    return fouls;
  }

  public int getFoulsAgainst() {
    return foulsAgainst;
  }

  public ShootingStats getFreeThrow() {
    return freeThrow;
  }

  public Game getGame() {
    return game;
  }

  public int getNumber() {
    return number;
  }

  public int getOffensiveRebounds() {
    return offensiveRebounds;
  }

  public Player getPlayer() {
    return player;
  }

  public int getPlayTime() {
    return playTime;
  }

  public int getSteals() {
    return steals;
  }

  public Team getTeam() {
    return team;
  }

  public ShootingStats getThreePoints() {
    return threePoints;
  }

  public int getTurnovers() {
    return turnovers;
  }

  public ShootingStats getTwoPoints() {
    return twoPoints;
  }

  @Override
  public int hashCode() {
    return hashOrNull(player) ^ hashOrNull(team) ^ hashOrNull(game);
  }

  public boolean isCaptain() {
    return isCaptain;
  }

  public boolean isCoach() {
    return isCoach;
  }

  public boolean isStarter() {
    return isStarter;
  }

  public void setAssists(int assists) {
    this.assists = assists;
  }

  public void setBlocks(int blocks) {
    this.blocks = blocks;
  }

  public void setBlocksAgainst(int blocksAgainst) {
    this.blocksAgainst = blocksAgainst;
  }

  public void setCaptain(boolean isCaptain) {
    this.isCaptain = isCaptain;
  }

  public void setCoach(boolean isCoach) {
    this.isCoach = isCoach;
  }

  public void setDefensiveRebounds(int defensiveRebounds) {
    this.defensiveRebounds = defensiveRebounds;
  }

  public void setFouls(int fouls) {
    this.fouls = fouls;
  }

  public void setFoulsAgainst(int foulsAgainst) {
    this.foulsAgainst = foulsAgainst;
  }

  public void setFreeThrow(ShootingStats freeThrow) {
    this.freeThrow = freeThrow;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public void setOffensiveRebounds(int offensiveRebounds) {
    this.offensiveRebounds = offensiveRebounds;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setPlayTime(int playTime) {
    this.playTime = playTime;
  }

  public void setStarter(boolean isStarter) {
    this.isStarter = isStarter;
  }

  public void setSteals(int steals) {
    this.steals = steals;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public void setThreePoints(ShootingStats threePoints) {
    this.threePoints = threePoints;
  }

  public void setTurnovers(int turnovers) {
    this.turnovers = turnovers;
  }

  public void setTwoPoints(ShootingStats twoPoints) {
    this.twoPoints = twoPoints;
  }

}

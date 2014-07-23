
package com.sencko.nalb.db;

import java.util.Date;

import com.google.appengine.api.datastore.Entity;

public class Game extends DBEntity {
  protected Tournament tournament;

  protected Date gameTime;

  protected Team homeTeam;

  protected Team awayTeam;

  protected String description;
  protected Integer id;

  public Game() {
    super();
  }

  public Game( Entity gameEntity) {
    super(gameEntity);
    tournament = (Tournament) getProperty("tournament");
    homeTeam = (Team) getProperty("homeTeam");
    awayTeam = (Team) getProperty("awayTeam");
    gameTime = (Date) getProperty("gameTime");
    description = (String) getProperty("description");
    id = (Integer) getProperty("id");

  }

  @Override
  protected Entity constructEntity() {
    setProperty("tournament", tournament);
    setProperty("homeTeam", homeTeam);
    setProperty("awayTeam", awayTeam);
    setProperty("gameTime", gameTime);
    setProperty("description", description);
    return entity;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Game) {
      Game other = (Game) obj;
      return equalsOrNull(id, other.id) && equalsOrNull(tournament, other.tournament) && equalsOrNull(gameTime, other.gameTime)
          && equalsOrNull(homeTeam, other.homeTeam) && equalsOrNull(awayTeam, other.awayTeam) && equalsOrNull(description, other.description);
    }
    return false;
  }

  public Team getAwayTeam() {
    return awayTeam;
  }

  public String getDescription() {
    return description;
  }

  public Date getGameTime() {
    return gameTime;
  }

  public Team getHomeTeam() {
    return homeTeam;
  }

  public Integer getId() {
    return id;
  }

  public Tournament getTournament() {
    return tournament;
  }

  @Override
  public int hashCode() {
    return hashOrNull(id) ^ hashOrNull(tournament) ^ hashOrNull(gameTime) ^ hashOrNull(homeTeam) ^ hashOrNull(awayTeam) ^ hashOrNull(description);
  }

  public void setAwayTeam(Team awayTeam) {
    this.awayTeam = awayTeam;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setGameTime(Date gameTime) {
    this.gameTime = gameTime;
  }

  public void setHomeTeam(Team homeTeam) {
    this.homeTeam = homeTeam;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setTournament(Tournament tournament) {
    this.tournament = tournament;
  }

}

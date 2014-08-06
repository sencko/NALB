
package com.sencko.nalb.db;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class RosterSpot extends DBEntity {
  public Tournament getTournament() {
    return tournament;
  }

  public void setTournament(Tournament tournament) {
    this.tournament = tournament;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  private Tournament tournament;
  private Player player;
  private Team team;
  private boolean defaultCaptain;

  public boolean isDefaultCaptain() {
    return defaultCaptain;
  }

  public RosterSpot( Entity gameEntity) {
    super(gameEntity);
    tournament = (Tournament) getProperty("tournament");
    player = (Player) getProperty("player");
    team = (Team) getProperty("team");
    defaultCaptain = (Boolean) getProperty("captain");
  }

  public RosterSpot() {
    super();
  }

  @Override
  protected Entity constructEntity() {
    setProperty("tournament", tournament);
    setProperty("player", player);
    setProperty("team", team);
    setProperty("captain", defaultCaptain);
    return entity;
  }

  @Override
  public int hashCode() {
    return hashOrNull(tournament) ^ hashOrNull(player) ^ hashOrNull(team);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof RosterSpot) {
      RosterSpot other = (RosterSpot) obj;
      return equalsOrNull(tournament, other.tournament) && equalsOrNull(team, other.team) && equalsOrNull(player, other.player);
    }
    return false;
  }

  public void setDefaultCaptain(boolean isCaptain) {
    defaultCaptain = isCaptain;

  }

  @Override
  public String toString() {
    return player.toString() + (defaultCaptain ? " (к)" : "");
  }

  public static List<RosterSpot> getPlayersForTeamInTournament(Tournament tour, Team team2) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Filter firstFilter = new FilterPredicate("tournament", FilterOperator.EQUAL, tour.getKey());
    Filter thirdFilter = new FilterPredicate("team", FilterOperator.EQUAL, team2.getKey());
    Filter composite = CompositeFilterOperator.and(firstFilter, thirdFilter);
    Query q = new Query(RosterSpot.class.getSimpleName()).setFilter(composite);
    PreparedQuery pq = datastore.prepare(q);
    List<RosterSpot> ret = new ArrayList<RosterSpot>();
    for (Entity en : pq.asIterable()) {
      ret.add(new RosterSpot(en));
    }
    return ret;
  }

}

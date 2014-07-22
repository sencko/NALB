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

}

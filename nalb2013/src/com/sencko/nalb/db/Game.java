package com.sencko.nalb.db;

import java.util.Date;

public class Game extends DBEntity {
  protected Tournament tournament;
  protected Date gameTime;
  protected Team homeTeam;
  protected Team awayTeam;
  protected String description;

}

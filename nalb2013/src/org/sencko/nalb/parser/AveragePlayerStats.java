package org.sencko.nalb.parser;

import java.util.Collections;
import java.util.Formatter;
import java.util.List;

public class AveragePlayerStats extends PlayerStats {
  
  List<PlayerStats> ps;
  int gamesPlayed;
  int gamesStarted;

  public AveragePlayerStats( Player player, List<PlayerStats> playerStats) {
    super(player);
    ps = Collections.unmodifiableList(playerStats);
    for (PlayerStats pstats:ps){
      add(pstats);
    }
  }
  
  @Override
  protected void add(PlayerStats player) {
    super.add(player);
    gamesPlayed++;
    gamesStarted+=player.starting?1:0;
  }

  static String AVERAGE_FORMAT = Util.readResource("average.html");
  
  public void toHTML(Formatter formatter) {
    formatter.format(AVERAGE_FORMAT, player.getName(), gamesPlayed, gamesStarted, (playTime/gamesPlayed) / 60, (playTime/gamesPlayed) % 60, twoPoints.made/(float) gamesPlayed,
        twoPoints.attempted/(float) gamesPlayed, twoPoints.getPercentage(), threePoints.made/(float) gamesPlayed, threePoints.attempted/(float) gamesPlayed, threePoints.getPercentage(), freeThrow.made/(float) gamesPlayed,
        freeThrow.attempted/(float) gamesPlayed, freeThrow.getPercentage(), offensiveRebounds/(float) gamesPlayed, defensiveRebounds/(float) gamesPlayed, (offensiveRebounds + defensiveRebounds)/(float) gamesPlayed, assists/(float) gamesPlayed,
        turnovers/(float) gamesPlayed, steals/(float) gamesPlayed, blocks/(float) gamesPlayed, blocksAgainst/(float) gamesPlayed, fouls/(float) gamesPlayed, foulsAgainst/(float) gamesPlayed, getPoints()/(float) gamesPlayed, getEfficiency()/(float) gamesPlayed
    );
  }
  
//  public void toHtml(Formatter formatter, PlayerStats ps){
//    
//    
//  }

}

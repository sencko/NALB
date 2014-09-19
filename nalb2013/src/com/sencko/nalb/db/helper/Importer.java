
package com.sencko.nalb.db.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.pdfbox.util.PDFTextStripper;
import org.sencko.nalb.parser.Game;
import org.sencko.nalb.parser.OptimizedCharArrayWriter;
import org.sencko.nalb.parser.PlayerStats;

import com.sencko.nalb.db.Alias;
import com.sencko.nalb.db.Player;
import com.sencko.nalb.db.PlayerStatistic;
import com.sencko.nalb.db.RosterSpot;
import com.sencko.nalb.db.Team;
import com.sencko.nalb.db.Tournament;

public class Importer {
  static Pattern pat = Pattern.compile(".?name");

  public static void importTeam(Tournament tournament, Team team, Properties players) {
    for (String key : players.stringPropertyNames()) {
      if (pat.matcher(key).matches()) {
        continue;
      } else {
        Player player = new Player();
        String[] names = key.split(" ");
        boolean isCaptain = false;
        int last = names.length - 1;
        if ("(к)".equalsIgnoreCase(names[last])) {
          isCaptain = true;
          last--;
        }
        player.setLastName(names[last]);
        player.setFirstName(names[0]);
        if (last == 2) {
          player.setMiddleName(names[1]);
        }

        String value = players.getProperty(key);
        String[] pValue = value.split("=");
        String[] date = pValue[0].split("\\.");
        Date bd = new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]) - 1);
        player.setDate(bd);
        int id;
        if (pValue.length > 1) {
          id = Integer.parseInt(pValue[1].trim());
        } else {
          // search for player, else
          Player.searchForPlayerExact(player.getFirstName(), player.getLastName());
          id = player.getFirstName().hashCode() ^ player.getLastName().hashCode() ^ bd.hashCode();
        }
        player.setId(new Long(id));
        player.save();
        RosterSpot spot = new RosterSpot();
        spot.setPlayer(player);
        spot.setTeam(team);
        spot.setTournament(tournament);
        spot.setDefaultCaptain(isCaptain);
        spot.save();
      }
    }

  }

  public static void importGame(Tournament tournament, InputStream pdfEncodedGame) throws Exception {

    PDFTextStripper stripper = new PDFTextStripper();
    stripper.setSortByPosition(true);
    OptimizedCharArrayWriter ocaw = new OptimizedCharArrayWriter();
    Game game = new Game(pdfEncodedGame, ocaw, stripper);
    com.sencko.nalb.db.Game dbGame = new com.sencko.nalb.db.Game();//com.sencko.nalb.db.Game.getGame(tournament, game.gameNo)
    Team homeTeam = Team.getTeam(tournament, game.homeTeam.getTeam().getAlias());
    
    Team awayTeam = Team.getTeam(tournament, game.awayTeam.getTeam().getAlias());
    dbGame.setAwayTeam(awayTeam);
    dbGame.setHomeTeam(homeTeam);
    dbGame.setTournament(tournament);
    dbGame.setId(game.gameNo);
    dbGame.setGameTime(game.date.getTime());
    dbGame.save();
    
    for(PlayerStats ps:game.homeTeam.getPlayerStats()){
     RosterSpot rs = RosterSpot.getRosterSpot(ps.name, homeTeam, tournament);
     Player player = rs.getPlayer();
     PlayerStatistic pstat = new PlayerStatistic();
     pstat.setGame(dbGame);
     pstat.setTeam(homeTeam);
     pstat.setPlayer(player);
     
     pstat.setAssists(ps.assists);
     pstat.setBlocks(ps.blocks);
     pstat.setBlocksAgainst(ps.blocksAgainst);
    // pstat.setCaptain(isCaptain);
    // pstat.setCoach(isCoach);
     pstat.setDefensiveRebounds(ps.defensiveRebounds);
     pstat.setFouls(ps.fouls);
     pstat.setFoulsAgainst(ps.foulsAgainst);
     pstat.setFreeThrow(ps.freeThrow);
     pstat.setNumber(ps.number);
     pstat.setPlayTime(ps.playTime);
     pstat.setStarter(ps.starting);
     pstat.setSteals(ps.steals);
     pstat.setThreePoints(ps.threePoints);
     pstat.setTurnovers(ps.turnovers);
     pstat.setTwoPoints(ps.twoPoints);
     pstat.save();
     
     
    }
    
  }

  public static void importAliases(Tournament tournament, Properties aliases) {
    for (String key : aliases.stringPropertyNames()) {
      Alias alias = new Alias();
      alias.setAliasKey(key);
      alias.setAliasValue(aliases.getProperty(key));
      alias.setTournament(tournament);
      alias.save();
    }
  }
}

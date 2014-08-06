
package com.sencko.nalb.db.helper;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import com.sencko.nalb.db.Alias;
import com.sencko.nalb.db.Player;
import com.sencko.nalb.db.RosterSpot;
import com.sencko.nalb.db.Team;
import com.sencko.nalb.db.Tournament;

public class Importer {

  public static void importTeam(Tournament tournament, Team team, Properties players) {
    for (String key : players.stringPropertyNames()) {
      if ("name".equals(key)) {
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

  public static void importGame(Tournament tournament, InputStream pdfEncodedGame) {

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

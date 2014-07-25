
package com.sencko.nalb.db.helper;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import com.sencko.nalb.db.Alias;
import com.sencko.nalb.db.Player;
import com.sencko.nalb.db.Team;
import com.sencko.nalb.db.Tournament;

public class Importer {

  public static void importTeam(Tournament tournament, Team team, Properties players) {
    for (String key : players.stringPropertyNames()) {
      Player player = new Player();
      String[] names = key.split(" ");
      
      int last = names.length - 1;
      if ("(к)".equalsIgnoreCase(names[last])){
        last --;
      }
      player.setLastName(names[last]);
      
      String value = players.getProperty(key);
      String[] pValue = value.split("=");
      String[] date = pValue[0].split("\\.");
      Date bd = new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]) - 1);
      int id;
      if (pValue.length > 1){
      id = Integer.parseInt(pValue[1].trim());
      } else {
        id = realName.hashCode() ^ bd.hashCode();
      }
      alias.setAliasKey(key);
      alias.setAliasValue();
      alias.setTournament(tournament);
      player.save();
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

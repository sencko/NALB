
package com.sencko.nalb.db.helper.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.gwt.dev.util.collect.HashMap;
import com.sencko.nalb.db.RosterSpot;
import com.sencko.nalb.db.Team;
import com.sencko.nalb.db.Tournament;
import com.sencko.nalb.db.helper.Importer;

public class ImporterTest {

  static LocalServiceTestHelper helper;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    LocalDatastoreServiceTestConfig config = new LocalDatastoreServiceTestConfig();

    helper = new LocalServiceTestHelper(config);
    helper.setUp();
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    helper.tearDown();
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void importTeamsRegular() throws IOException {
    Tournament tournament = new Tournament();
    tournament.setName("2013/2014 regular");
    tournament.setName("НАЛБ 2013/2014 редовен сезон");
    tournament.setParent(null);
    tournament.setSchema("REGULAR");
    tournament.save();
    Properties aliases = new Properties();
    aliases.load(new InputStreamReader(ImporterTest.class.getResourceAsStream("properties/regular/aliases.properties"), "UTF-8"));

    Importer.importAliases(tournament, aliases);
    HashMap<String, String> teamNames = new HashMap<String, String>();
    teamNames.put("BLS", "Блейдс");
    teamNames.put("BULS", "София Булс");
    teamNames.put("CRAZY", "Крейзи Малакес");
    teamNames.put("DAR", "Дървоядите");
    teamNames.put("HEL", "Хелга");
    teamNames.put("LAV", "Лъвовете");
    teamNames.put("LNX", "Лонекс");
    teamNames.put("LOKO", "Локомотив");
    teamNames.put("OLD", "Олд Таймърс");
    teamNames.put("PAL", "Паляците");
    teamNames.put("PEIPAR", "Пейпър Кор Костенец");
    teamNames.put("ZEL", "Зелените");
    for (String key : teamNames.keySet()) {
      Team team = Team.getTeam(key, teamNames.get(key));
      Properties teamProperties = new Properties();
      teamProperties.load(new InputStreamReader(ImporterTest.class.getResourceAsStream("properties/regular/" + key + ".properties"), "UTF-8"));
      Importer.importTeam(tournament, team, teamProperties);
    }

    List<Tournament> tournaments = Tournament.getTournaments();
    for (Tournament tour : tournaments) {
      System.out.println(tour);
      List<Team> teams = Team.getTeams(tour);

      for (Team team : teams) {
        System.out.print("\t");
        System.out.println(team);
        List<RosterSpot> participants = RosterSpot.getPlayersForTeamInTournament(tour, team);
        for (RosterSpot spot : participants) {
          System.out.print("\t\t");
          System.out.println(spot);
        }
      }

    }
  }

  @Test
  public void importGamesRegular() throws Exception {
    Tournament tournament = Tournament.getTournaments().get(0);
    for (int i = 1; i <= 132; i++) {
      InputStream pdfStream = ImporterTest.class.getResourceAsStream("pdf/2013/" + i + ".pdf");
      Importer.importGame(tournament, pdfStream);
    }
  }

}

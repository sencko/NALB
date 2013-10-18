/**
 * Copyright (c) 2013 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP AG, Walldorf. You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with SAP.
 * 
 * Created on Oct 9, 2013 by I028512
 * 
 */

package org.sencko.nalb.parser;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class Game {
  private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("EET");
  static final Pattern teamMatcher = Pattern.compile("(\\S+)\\s+(\\d+)\\s+\\-\\s+(\\d+)\\s+(\\S+).*");
  static final Pattern gameNumber = Pattern.compile(".+\\s+(\\d+)\\s*");
  static Pattern playerStats = Pattern
      .compile("(\\*)*\\s*(\\d+)\\s+(.+)\\s+(\\d+):(\\d+)\\s+(\\d+)/(\\d+)\\s+(\\d+)\\s+(\\d+)/(\\d+)\\s+(\\d+)\\s+(\\d+)/(\\d+)\\s+(\\d+)\\s+(\\d+)/(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+).*");
  static Pattern teamTotals = Pattern.compile("Team Totals:\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*");
  static Pattern dateTime = Pattern.compile("Date:\\s+(\\d+)\\.(\\d+)\\.(\\d+)\\s+Time:\\s+(\\d+):(\\d+)\\s+.*");
  static SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", new Locale("bg"));
  
  static String FORMAT = Util.readResource("game.html");


  String homeTeam;
  String awayTeam;
  Calendar date;

  List<Integer> scoring5minHome = new ArrayList<Integer>();
  List<Integer> scoring5minAway = new ArrayList<Integer>();
  ArrayList<PlayerStats> playersStatsHome = new ArrayList<PlayerStats>();
  ArrayList<PlayerStats> playersStatsAway = new ArrayList<PlayerStats>();
  BufferedReader reader;
  int gameNo;
  
  
  transient String rowCache = null;
  transient Matcher currentMatcher = null;

  public Game( InputStream stream,OptimizedCharArrayWriter writer,PDFTextStripper stripper) throws Exception {
    PDDocument document = PDDocument.load(stream);

    
    writer.reset();

    stripper.writeText(document, writer);

    reader = new BufferedReader(new CharArrayReader(writer.getBuffer(),0,writer.size()));
    try {
      extractTeamNames();
      extractGameNumber();
      extractMatchTime();
      extract5minPeriodScore(scoring5minHome, homeTeam);
      extract5minPeriodScore(scoring5minAway, awayTeam);
      extractPlayerStats(playersStatsHome, homeTeam);
      extractPlayerStats(playersStatsAway, awayTeam);

      document.close();
      rowCache = null;
      currentMatcher = null;
    } catch (Exception ex) {
      System.out.println(rowCache);
      System.out.println(currentMatcher);
      throw ex;
    }
  }

  private void extractMatchTime() throws IOException {
    do {
      rowCache = readLine();
      currentMatcher = dateTime.matcher(rowCache);
    } while (!currentMatcher.matches());
    date = Calendar.getInstance(TIME_ZONE);
    date.set(Integer.parseInt(currentMatcher.group(3)), Integer.parseInt(currentMatcher.group(2)) - 1, Integer.parseInt(currentMatcher.group(1)),
        Integer.parseInt(currentMatcher.group(4)), Integer.parseInt(currentMatcher.group(5)), 0);
    if (date.get(Calendar.YEAR) < 2010) {
      date.set(Calendar.YEAR, 2013);
    }
  }

  private void extractGameNumber() throws IOException {
    rowCache = readLine();
    currentMatcher = gameNumber.matcher(rowCache);
    currentMatcher.matches();
    gameNo = Integer.parseInt(currentMatcher.group(1));
  }

  private void extractTeamNames() throws IOException {
    rowCache = readLine();
    currentMatcher = teamMatcher.matcher(rowCache);
    if (currentMatcher.matches()) {
      homeTeam = currentMatcher.group(1);
      awayTeam = currentMatcher.group(4);
    } else {
      throw new RuntimeException("Head error");
    }
  }

  private void extractPlayerStats(ArrayList<PlayerStats> playerStatistics, String teamName) throws IOException {
      String teamResolved = Util.getTeamInitial(teamName);
      do {
        rowCache = readLine();
        currentMatcher = playerStats.matcher(rowCache);
      } while (!currentMatcher.matches());
      PlayerStats team = new PlayerStats("Общо");
      Matcher totalsMatcher = null;
      do {
        if (currentMatcher.matches()) {
          PlayerStats ps = new PlayerStats(currentMatcher, teamResolved);
          playerStatistics.add(ps);
          team.add(ps);
        }
        rowCache = readLine();
        currentMatcher = playerStats.matcher(rowCache);
      } while (!(totalsMatcher = teamTotals.matcher(rowCache)).matches());
      TeamTotals teamTotals = new TeamTotals(totalsMatcher);
      playerStatistics.add(teamTotals);
      team.add(teamTotals);
      playerStatistics.add(team);
  }

  private void extract5minPeriodScore(List<Integer> param, String teamName) throws IOException {
    Pattern fiveMinPatternHome = Pattern.compile(teamName + "(\\s+(\\d+)){8,}\\s*");
    do {
      rowCache = readLine();
      currentMatcher = fiveMinPatternHome.matcher(rowCache);
    } while (!currentMatcher.matches());
    
    String[] splitted = rowCache.split("\\s");
    for (int i = 1; i < splitted.length; i++) {
      param.add(Integer.parseInt(splitted[i]));
    }
  }

  private String readLine() throws IOException {
    String text = reader.readLine();
//    System.err.println(text);
    return text;
  }


  public void toHtml(Formatter formatter) {
    formatter.format(FORMAT, getHomeTeam(), getAwayTeam(), getHomeTeam(), getAwayTeam(), getScoreByPeriods(), getHomeTeam(),
        getPlayersToHtml(playersStatsHome.subList(0, playersStatsHome.size() - 2)),
        getPlayersToHtml(playersStatsHome.subList(playersStatsHome.size() - 2, playersStatsHome.size())), getAwayTeam(),
        getPlayersToHtml(playersStatsAway.subList(0, playersStatsAway.size() - 2)),
        getPlayersToHtml(playersStatsAway.subList(playersStatsAway.size() - 2, playersStatsAway.size())));
  }

  private String getScoreByPeriods() {
    StringBuilder sb = new StringBuilder(800);
    sb.append("<h3>");

    sb.append(sdf.format(date.getTime()));
    sb.append("</h3>");
    sb.append("<table border=\"1\" ><thead>");
    sb.append("<tr><th>Отбор</th>");
    for (int i = 0; i < scoring5minHome.size(); i++) {
      sb.append("<th>").append((i + 1) * 5 + "'").append("</th>");
    }
    sb.append("<th>Общо</th>");
    sb.append("</tr></thead><tbody>");
    
    scoringPerPeriodInHtml(sb, getHomeTeam(), scoring5minHome);
    scoringPerPeriodInHtml(sb, getAwayTeam(), scoring5minAway);
    
    sb.append("</tbody></table>");
    return sb.toString();
  }

  private void scoringPerPeriodInHtml(StringBuilder sb, String teamName, List<Integer> param) {
    sb.append("<tr><th>");
    sb.append(teamName);
    sb.append("</th>");
    sb.append("<td>").append(param.get(0)).append("</td>");
    for (int i = 1; i < param.size(); i++) {
      sb.append("<td>").append(param.get(i) - param.get(i - 1)).append("</td>");
    }
    sb.append("<th>").append(param.get(param.size() - 1)).append("</th>");
    sb.append("</tr>");
  }

  private String getPlayersToHtml(List<PlayerStats> playersStatsHome2) {
    StringBuilder builder = new StringBuilder();
    Formatter formatter = new Formatter(builder);
    for (PlayerStats ps : playersStatsHome2) {
      ps.toHTML(formatter);
    }
    return builder.toString();
  }

  private String getAwayTeam() {
    return Util.getTeamName(awayTeam);
  }

  private String getHomeTeam() {
    return Util.getTeamName(homeTeam);

  }

}
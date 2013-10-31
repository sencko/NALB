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

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.Formatter;

import org.apache.pdfbox.util.PDFTextStripper;

public class PDFFormatParser {
  
  public static boolean includeScripts = true;
  public static String style = null;
  public static String javaScript = null;
  public static String styleToReplace = "<link rel=\"stylesheet\" type=\"text/css\" href=\"nalb.css\"/>";
  public static String scriptToReplace = "<script type=\"text/javascript\" src=\"gs_sortable.js\"></script>";
  public static void main(String[] args) throws Exception {// PDFText2HTML
    // PDDocument document = PDDocument.load(PDFFormatParser.class.getResourceAsStream("6.pdf"));
    // PDFTextStripper stripper = new PDFTextStripper();
    // stripper.setSortByPosition(true);
    // // PDFText2HTML stripper = new PDFText2HTML("UTF-8");
    // StringWriter writer = new StringWriter();
    // stripper.writeText(document, writer);
    //
    // System.out.println(writer.toString());
    // System.out.println(Pattern.compile("(\\S+)\\s+(\\d+)\\s+\\-\\s+(\\d+)\\s+(\\S+).*").matcher("DAR  46 - 69  OLD Box Score-Full Game").matches());
    // Game game = new Game(PDFFormatParser.class.getResourceAsStream("pdf/12.pdf"));
    // FileOutputStream fos = new FileOutputStream("test.html");
    // fos.write(game.toHtml().getBytes("UTF-8"));
    // fos.close();
    if (includeScripts){
      style = "<style type=\"text/css\">" + Util.readResource("nalb.css") + "</style>";
      javaScript = "<script type=\"text/javascript\"><!--" + Util.readResource("gs_sortable.js") + "// --></script>";
      Game.FORMAT = Game.FORMAT.replace(styleToReplace, style).replace(scriptToReplace,javaScript);
      Team.TEAM_STATS = Team.TEAM_STATS.replace(styleToReplace, style).replace(scriptToReplace,javaScript);
    }
    
    File folder = new File("pdf");
    File[] files = folder.listFiles(new FilenameFilter() {

      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".pdf");
      }
    });
    PDFTextStripper stripper = new PDFTextStripper();
    stripper.setSortByPosition(true);
    OptimizedCharArrayWriter ocaw = new OptimizedCharArrayWriter();
    new File("results").mkdirs();
    for (File file : files) {
      try {
        System.out.println(file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
        Game game = new Game(fis, ocaw, stripper);
        Formatter formatter = new Formatter("results/" + game.homeTeam.getTeam().getAlias() + "-" + game.awayTeam.getTeam().getAlias() + game.gameNo + ".html", "UTF-8");
        game.toHtml(formatter);
        formatter.close();
      } catch (Exception ex) {

        ex.printStackTrace();
      }
    }
    Team allTeam = new Team();
    for (Team team: Team.teamSet){
      try {
        allTeam.add(team);
        Formatter formatter = new Formatter("results/" + team.getAlias() + ".html", "UTF-8");
        team.toHtml(formatter);
        formatter.close();
      } catch (Exception ex) {

        ex.printStackTrace();
      }      
    }
    
    try {
      Formatter formatter = new Formatter("results/" + allTeam.getAlias() + ".html", "UTF-8");
      allTeam.toHtml(formatter);
      formatter.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }      

  }

}

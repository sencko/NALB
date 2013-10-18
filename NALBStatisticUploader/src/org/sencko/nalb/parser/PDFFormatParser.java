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
import java.io.FileOutputStream;
import java.io.FilenameFilter;

public class PDFFormatParser {
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
    File folder = new File("pdf");
    File[] files = folder.listFiles(new FilenameFilter() {

      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".pdf");
      }
    });
    new File("results").mkdirs();
    for (File file : files) {
      try {
        System.out.println(file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
        Game game = new Game(fis);
        FileOutputStream fos = new FileOutputStream("results/" + game.homeTeam + "-" + game.awayTeam + game.gameNo + ".html");
        fos.write(game.toHtml().getBytes("UTF-8"));
        fos.close();
      } catch (Exception ex) {

        ex.printStackTrace();
      }
    }

  }

}

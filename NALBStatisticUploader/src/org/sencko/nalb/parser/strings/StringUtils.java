/**
 * Copyright (c) 2013 by SAP Labs Bulgaria,
 * url: http://www.sap.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of SAP AG, Walldorf. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with SAP.
 * 
 * Created on Oct 16, 2013 by I028512
 *   
 */
 
package org.sencko.nalb.parser.strings;

import org.sencko.nalb.parser.Util;

public class StringUtils {
  public static String transliterate(String string) {
    StringBuilder b = new StringBuilder(string.length());
    for (int i = 0; i < string.length(); i++) {
      char c = string.charAt(i);
      boolean toLowerCase = false;
      if ((c >= 'а') && (c <= 'я')) {
        toLowerCase = true;
        c = Character.toUpperCase(c);
      }
      String toAdd = null;
      if ((c >= 'А') && (c <= 'Я')) {
        toAdd = Util.getProperties("trans").getProperty(Character.toString(c));
        if (toLowerCase) {
          toAdd = toAdd.toLowerCase();
        }
        b.append(toAdd);
      } else {
        b.append(c);
      }
    }
    return b.toString();
  }
  
  public static void main(String[] args){
    System.out.println(transliterate("Аз съм Сашо"));
  }

}

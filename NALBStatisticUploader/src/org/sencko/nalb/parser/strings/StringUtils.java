/**
 * Copyright (c) 2013 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP AG, Walldorf. You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with SAP.
 * 
 * Created on Oct 16, 2013 by I028512
 * 
 */

package org.sencko.nalb.parser.strings;

import java.util.HashMap;
import java.util.Properties;

import org.sencko.nalb.parser.Util;

public class StringUtils {

  static String[] trans = new String['я' - 'А' + 1];
  static {
    Properties p = Util.getProperties("trans");
    for (String key : p.stringPropertyNames()) {
      if (key.length() == 1) {
        trans[key.charAt(0) - 'А'] = p.getProperty(key);
      }
    }
  }

  public static String transliterate(String string) {
    StringBuilder b = new StringBuilder(string.length());
    for (int i = 0; i < string.length(); i++) {
      char c = string.charAt(i);
      String toAdd = null;
      if (((c >= 'а') && (c <= 'я')) || ((c >= 'А') && (c <= 'Я'))) {
        toAdd = trans[c - 'А'];
        // toAdd = Util.getProperties("trans").getProperty(Character.toString(c));
        b.append(toAdd);
      } else {
        b.append(c);
      }
    }
    return b.toString();
  }

  static HashMap<String, String> map = new HashMap<String, String>();

  public static String transliterateWithCache(String string) {
    String ret = map.get(string);
    if (ret == null) {
      ret = transliterate(string);
      String[] name = ret.split(" ");
      if ("(k)".equals(name[name.length - 1])) {
        ret = name[name.length - 2] + " " + name[0];
      } else {
        ret = name[name.length - 1] + " " + name[0];
      }
      map.put(string, ret);
    }
    return ret;
  }

  public static void main(String[] args) {
    System.out.println(transliterate("Аз съм Сашо"));
  }

}

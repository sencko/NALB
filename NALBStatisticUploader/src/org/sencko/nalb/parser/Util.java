/**
 * Copyright (c) 2013 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP AG, Walldorf. You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with SAP.
 * 
 * Created on Oct 14, 2013 by I028512
 * 
 */

package org.sencko.nalb.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Properties;

import org.sencko.nalb.parser.strings.DamerauLevenshteinAlgorithm;

public class Util {

  static HashMap<String, Properties> cache = new HashMap<String, Properties>();

  public static String readResource(String resourceName) {
    InputStream is = Util.class.getResourceAsStream("resources/" + resourceName);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    int temp = -1;
    try {
      while ((temp = is.read()) != -1) {
        bos.write(temp);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Charset.forName("UTF-8").decode(ByteBuffer.wrap(bos.toByteArray())).toString();
  }

  public static Properties getProperties(String propertiesKey) {
    Properties ret = cache.get(propertiesKey);
    if (ret == null) {
      ret = new Properties();
      try {
        InputStream is = Util.class.getResourceAsStream("properties/" + propertiesKey + ".properties");
        is.read();
        is.read();
        is.read();
        ret.load(new InputStreamReader(is, "UTF-8"));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
      // Enumeration en =ret.keys();
      // while(en.hasMoreElements()){
      // String key = (String) en.nextElement();
      // System.out.println("key:" + key + " " + key.hashCode());
      // System.out.println("value:" + ret.getProperty(key));
      // }

      // try {
      // System.out.println(new String("name".getBytes("UTF-8"), "UTF-8").hashCode());
      // } catch (UnsupportedEncodingException e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
      cache.put(propertiesKey, ret);
    }
    return ret;
  }

  public static String getTeamName(String homeTeam) {
    String original = getTeamInitial(homeTeam);
    return Util.getProperties(original).getProperty("name");
  }

  public static String getTeamInitial(String homeTeam) {
    return Util.getProperties("aliases").getProperty(resolveDLA(Util.getProperties("aliases"), homeTeam));
  }
  static DamerauLevenshteinAlgorithm dla = new DamerauLevenshteinAlgorithm(1, 1, 1, 1);
  public static String resolveDLA(Properties properties, String homeTeam) {
    return dla.getClosest(homeTeam, properties.stringPropertyNames());
  }

  public static String resolveAlias(String alias) {
    String original = Util.getProperties("aliases").getProperty(alias);
    if (original == null) {
      original = alias;
    }
    return original;
  }
}
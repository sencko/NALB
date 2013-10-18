/**
 * Copyright (c) 2013 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP AG, Walldorf. You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with SAP.
 * 
 * Created on Oct 11, 2013 by I028512
 * 
 */

package org.sencko.nalb.parser;

import java.util.regex.Matcher;

public class TeamTotals extends PlayerStats {
  public TeamTotals( Matcher matcher) {
    super("Отборно");
    offensiveRebounds = Integer.parseInt(matcher.group(1));
    defensiveRebounds = Integer.parseInt(matcher.group(2));
    turnovers = Integer.parseInt(matcher.group(4));
    fouls = Integer.parseInt(matcher.group(5));

  }

  @Override
  public String toString() {
    return String.format("%2s %30s %4s %5s %5s %5s %2d %2d %2s %2d %2s %2s %2s %2d %2s %3s\n", "", name, "", "", "", "", offensiveRebounds,
        defensiveRebounds, "", turnovers, "", "", "", fouls, "", "");
  }

  static String TOTALS_FORMAT = Util.readResource("totals.html");

  public String toHTML() {
    return String.format(TOTALS_FORMAT, name,  offensiveRebounds, defensiveRebounds, offensiveRebounds
        + defensiveRebounds,  turnovers, fouls);
  }
}

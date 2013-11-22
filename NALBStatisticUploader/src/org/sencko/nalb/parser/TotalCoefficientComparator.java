package org.sencko.nalb.parser;

import java.util.Comparator;

public class TotalCoefficientComparator implements Comparator<Player> {

  @Override
  public int compare(Player o1, Player o2) {
    return o2.getAveragePlayerStats().getEfficiency() - o1.getAveragePlayerStats().getEfficiency();
  }

}

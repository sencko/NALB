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
 * Created on Oct 9, 2013 by I028512
 *   
 */
 
package org.sencko.nalb.parser;

public class ShootingStats {
  int made;
  int attempted;

  public ShootingStats( int parseInt, int parseInt2) {
    made = parseInt;
    attempted = parseInt2;
  }
  
  @Override
  public String toString(){
    return String.format("%2d/%2d", made, attempted);
  }

  public void add(ShootingStats other) {
    if (other != null){
    made += other.made;
    attempted += other.attempted;
    }
  }

  public float getPercentage() {
    if (attempted == 0){
      return 0f;
    } else {
      float ret = made;
      ret /= attempted;
      ret *= 100;
      return ret;
    }
  }

}

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
 * Created on Oct 18, 2013 by I028512
 *   
 */
 
package org.sencko.nalb.parser;

import java.io.CharArrayWriter;

public class OptimizedCharArrayWriter extends CharArrayWriter {
   public char[] getBuffer(){
     return buf;
   }

}

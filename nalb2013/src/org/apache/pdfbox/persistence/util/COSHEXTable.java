/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

package org.apache.pdfbox.persistence.util;

import org.apache.pdfbox.util.StringUtil;

/**
 * helper type for faster mapping of bytes to their hex equivalent.
 * 
 * @author Michael Traut
 * @version $Revision: 1.4 $
 */
public final class COSHEXTable {
  private COSHEXTable() {
  }

  /**
   * ASCII byte values for the hex strings.
   */
  public static final byte[][] TABLE = { StringUtil.getBytes("00"), StringUtil.getBytes("01"), StringUtil.getBytes("02"), StringUtil.getBytes("03"),
      StringUtil.getBytes("04"), StringUtil.getBytes("05"), StringUtil.getBytes("06"), StringUtil.getBytes("07"), StringUtil.getBytes("08"),
      StringUtil.getBytes("09"), StringUtil.getBytes("0A"), StringUtil.getBytes("0B"), StringUtil.getBytes("0C"), StringUtil.getBytes("0D"),
      StringUtil.getBytes("0E"), StringUtil.getBytes("0F"), StringUtil.getBytes("10"), StringUtil.getBytes("11"), StringUtil.getBytes("12"),
      StringUtil.getBytes("13"), StringUtil.getBytes("14"), StringUtil.getBytes("15"), StringUtil.getBytes("16"), StringUtil.getBytes("17"),
      StringUtil.getBytes("18"), StringUtil.getBytes("19"), StringUtil.getBytes("1A"), StringUtil.getBytes("1B"), StringUtil.getBytes("1C"),
      StringUtil.getBytes("1D"), StringUtil.getBytes("1E"), StringUtil.getBytes("1F"), StringUtil.getBytes("20"), StringUtil.getBytes("21"),
      StringUtil.getBytes("22"), StringUtil.getBytes("23"), StringUtil.getBytes("24"), StringUtil.getBytes("25"), StringUtil.getBytes("26"),
      StringUtil.getBytes("27"), StringUtil.getBytes("28"), StringUtil.getBytes("29"), StringUtil.getBytes("2A"), StringUtil.getBytes("2B"),
      StringUtil.getBytes("2C"), StringUtil.getBytes("2D"), StringUtil.getBytes("2E"), StringUtil.getBytes("2F"), StringUtil.getBytes("30"),
      StringUtil.getBytes("31"), StringUtil.getBytes("32"), StringUtil.getBytes("33"), StringUtil.getBytes("34"), StringUtil.getBytes("35"),
      StringUtil.getBytes("36"), StringUtil.getBytes("37"), StringUtil.getBytes("38"), StringUtil.getBytes("39"), StringUtil.getBytes("3A"),
      StringUtil.getBytes("3B"), StringUtil.getBytes("3C"), StringUtil.getBytes("3D"), StringUtil.getBytes("3E"), StringUtil.getBytes("3F"),
      StringUtil.getBytes("40"), StringUtil.getBytes("41"), StringUtil.getBytes("42"), StringUtil.getBytes("43"), StringUtil.getBytes("44"),
      StringUtil.getBytes("45"), StringUtil.getBytes("46"), StringUtil.getBytes("47"), StringUtil.getBytes("48"), StringUtil.getBytes("49"),
      StringUtil.getBytes("4A"), StringUtil.getBytes("4B"), StringUtil.getBytes("4C"), StringUtil.getBytes("4D"), StringUtil.getBytes("4E"),
      StringUtil.getBytes("4F"), StringUtil.getBytes("50"), StringUtil.getBytes("51"), StringUtil.getBytes("52"), StringUtil.getBytes("53"),
      StringUtil.getBytes("54"), StringUtil.getBytes("55"), StringUtil.getBytes("56"), StringUtil.getBytes("57"), StringUtil.getBytes("58"),
      StringUtil.getBytes("59"), StringUtil.getBytes("5A"), StringUtil.getBytes("5B"), StringUtil.getBytes("5C"), StringUtil.getBytes("5D"),
      StringUtil.getBytes("5E"), StringUtil.getBytes("5F"), StringUtil.getBytes("60"), StringUtil.getBytes("61"), StringUtil.getBytes("62"),
      StringUtil.getBytes("63"), StringUtil.getBytes("64"), StringUtil.getBytes("65"), StringUtil.getBytes("66"), StringUtil.getBytes("67"),
      StringUtil.getBytes("68"), StringUtil.getBytes("69"), StringUtil.getBytes("6A"), StringUtil.getBytes("6B"), StringUtil.getBytes("6C"),
      StringUtil.getBytes("6D"), StringUtil.getBytes("6E"), StringUtil.getBytes("6F"), StringUtil.getBytes("70"), StringUtil.getBytes("71"),
      StringUtil.getBytes("72"), StringUtil.getBytes("73"), StringUtil.getBytes("74"), StringUtil.getBytes("75"), StringUtil.getBytes("76"),
      StringUtil.getBytes("77"), StringUtil.getBytes("78"), StringUtil.getBytes("79"), StringUtil.getBytes("7A"), StringUtil.getBytes("7B"),
      StringUtil.getBytes("7C"), StringUtil.getBytes("7D"), StringUtil.getBytes("7E"), StringUtil.getBytes("7F"), StringUtil.getBytes("80"),
      StringUtil.getBytes("81"), StringUtil.getBytes("82"), StringUtil.getBytes("83"), StringUtil.getBytes("84"), StringUtil.getBytes("85"),
      StringUtil.getBytes("86"), StringUtil.getBytes("87"), StringUtil.getBytes("88"), StringUtil.getBytes("89"), StringUtil.getBytes("8A"),
      StringUtil.getBytes("8B"), StringUtil.getBytes("8C"), StringUtil.getBytes("8D"), StringUtil.getBytes("8E"), StringUtil.getBytes("8F"),
      StringUtil.getBytes("90"), StringUtil.getBytes("91"), StringUtil.getBytes("92"), StringUtil.getBytes("93"), StringUtil.getBytes("94"),
      StringUtil.getBytes("95"), StringUtil.getBytes("96"), StringUtil.getBytes("97"), StringUtil.getBytes("98"), StringUtil.getBytes("99"),
      StringUtil.getBytes("9A"), StringUtil.getBytes("9B"), StringUtil.getBytes("9C"), StringUtil.getBytes("9D"), StringUtil.getBytes("9E"),
      StringUtil.getBytes("9F"), StringUtil.getBytes("A0"), StringUtil.getBytes("A1"), StringUtil.getBytes("A2"), StringUtil.getBytes("A3"),
      StringUtil.getBytes("A4"), StringUtil.getBytes("A5"), StringUtil.getBytes("A6"), StringUtil.getBytes("A7"), StringUtil.getBytes("A8"),
      StringUtil.getBytes("A9"), StringUtil.getBytes("AA"), StringUtil.getBytes("AB"), StringUtil.getBytes("AC"), StringUtil.getBytes("AD"),
      StringUtil.getBytes("AE"), StringUtil.getBytes("AF"), StringUtil.getBytes("B0"), StringUtil.getBytes("B1"), StringUtil.getBytes("B2"),
      StringUtil.getBytes("B3"), StringUtil.getBytes("B4"), StringUtil.getBytes("B5"), StringUtil.getBytes("B6"), StringUtil.getBytes("B7"),
      StringUtil.getBytes("B8"), StringUtil.getBytes("B9"), StringUtil.getBytes("BA"), StringUtil.getBytes("BB"), StringUtil.getBytes("BC"),
      StringUtil.getBytes("BD"), StringUtil.getBytes("BE"), StringUtil.getBytes("BF"), StringUtil.getBytes("C0"), StringUtil.getBytes("C1"),
      StringUtil.getBytes("C2"), StringUtil.getBytes("C3"), StringUtil.getBytes("C4"), StringUtil.getBytes("C5"), StringUtil.getBytes("C6"),
      StringUtil.getBytes("C7"), StringUtil.getBytes("C8"), StringUtil.getBytes("C9"), StringUtil.getBytes("CA"), StringUtil.getBytes("CB"),
      StringUtil.getBytes("CC"), StringUtil.getBytes("CD"), StringUtil.getBytes("CE"), StringUtil.getBytes("CF"), StringUtil.getBytes("D0"),
      StringUtil.getBytes("D1"), StringUtil.getBytes("D2"), StringUtil.getBytes("D3"), StringUtil.getBytes("D4"), StringUtil.getBytes("D5"),
      StringUtil.getBytes("D6"), StringUtil.getBytes("D7"), StringUtil.getBytes("D8"), StringUtil.getBytes("D9"), StringUtil.getBytes("DA"),
      StringUtil.getBytes("DB"), StringUtil.getBytes("DC"), StringUtil.getBytes("DD"), StringUtil.getBytes("DE"), StringUtil.getBytes("DF"),
      StringUtil.getBytes("E0"), StringUtil.getBytes("E1"), StringUtil.getBytes("E2"), StringUtil.getBytes("E3"), StringUtil.getBytes("E4"),
      StringUtil.getBytes("E5"), StringUtil.getBytes("E6"), StringUtil.getBytes("E7"), StringUtil.getBytes("E8"), StringUtil.getBytes("E9"),
      StringUtil.getBytes("EA"), StringUtil.getBytes("EB"), StringUtil.getBytes("EC"), StringUtil.getBytes("ED"), StringUtil.getBytes("EE"),
      StringUtil.getBytes("EF"), StringUtil.getBytes("F0"), StringUtil.getBytes("F1"), StringUtil.getBytes("F2"), StringUtil.getBytes("F3"),
      StringUtil.getBytes("F4"), StringUtil.getBytes("F5"), StringUtil.getBytes("F6"), StringUtil.getBytes("F7"), StringUtil.getBytes("F8"),
      StringUtil.getBytes("F9"), StringUtil.getBytes("FA"), StringUtil.getBytes("FB"), StringUtil.getBytes("FC"), StringUtil.getBytes("FD"),
      StringUtil.getBytes("FE"), StringUtil.getBytes("FF") };

  /**
   * ASCII byte values for the hex strings.
   */
  public static final String[] HEX_TABLE = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10",
      "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28",
      "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40",
      "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58",
      "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70",
      "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88",
      "89", "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0",
      "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8",
      "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0",
      "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8",
      "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };
}

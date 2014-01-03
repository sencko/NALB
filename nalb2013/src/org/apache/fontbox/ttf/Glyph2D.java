/*
 * 
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

package org.apache.fontbox.ttf;

// import java.awt.geom.GeneralPath;
// import java.awt.geom.Point2D;

/**
 * This class provides a glyph to GeneralPath conversion.
 * 
 * This class is based on code from Apache Batik a subproject of Apache XMLGraphics. see http://xmlgraphics.apache.org/batik/ for further details.
 */
public class Glyph2D {

  private short leftSideBearing = 0;
  private int advanceWidth = 0;
  private Point[] points;

  // private GeneralPath glyphPath;

  /**
   * Constructor.
   * 
   * @param gd the glyph description
   * @param lsb leftSideBearing
   * @param advance advanceWidth
   */
  public Glyph2D( GlyphDescription gd, short lsb, int advance) {
    leftSideBearing = lsb;
    advanceWidth = advance;
    describe(gd);
  }

  /**
   * Returns the advanceWidth value.
   * 
   * @return the advanceWidth
   */
  public int getAdvanceWidth() {
    return advanceWidth;
  }

  /**
   * Returns the leftSideBearing value.
   * 
   * @return the leftSideBearing
   */
  public short getLeftSideBearing() {
    return leftSideBearing;
  }

  /**
   * Set the points of a glyph from the GlyphDescription.
   */
  private void describe(GlyphDescription gd) {
    int endPtIndex = 0;
    points = new Point[gd.getPointCount()];
    for (int i = 0; i < gd.getPointCount(); i++) {
      boolean endPt = gd.getEndPtOfContours(endPtIndex) == i;
      if (endPt) {
        endPtIndex++;
      }
      points[i] = new Point(gd.getXCoordinate(i), gd.getYCoordinate(i), (gd.getFlags(i) & GlyfDescript.ON_CURVE) != 0, endPt);
    }
  }

  /**
   * This class represents one point of a glyph.
   * 
   */
  private class Point {

    public Point( int xValue, int yValue, boolean onCurveValue, boolean endOfContourValue) {
    }
  }

}

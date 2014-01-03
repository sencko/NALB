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

package org.apache.pdfbox.util;

// import java.awt.image.BufferedImage;
// import java.awt.image.RenderedImage;
// import javax.imageio.IIOException;
// import javax.imageio.IIOImage;
// import javax.imageio.ImageIO;
// import javax.imageio.ImageTypeSpecifier;
// import javax.imageio.ImageWriteParam;
// import javax.imageio.ImageWriter;
// import javax.imageio.metadata.IIOInvalidTreeException;
// import javax.imageio.metadata.IIOMetadata;
// import javax.imageio.metadata.IIOMetadataNode;
// import javax.imageio.stream.ImageOutputStream;

/**
 * This class handles some ImageIO operations.
 * 
 * @version $Revision$
 * 
 */
public class ImageIOUtil {
  /**
   * Default screen resolution: 72dpi.
   */
  public static final int DEFAULT_SCREEN_RESOLUTION = 72;
  /**
   * Default compression quality: 1.0f.
   */
  public static final float DEFAULT_COMPRESSION_QUALITY = 1.0f;

  private ImageIOUtil() {
    // Default constructor
  }

  /**
   * Writes a buffered image to a file using the given image format.
   * 
   * @param image the image to be written
   * @param imageFormat the target format (ex. "png")
   * @param filename used to construct the filename for the individual images
   * @param imageType the image type (see {@link BufferedImage}.TYPE_*)
   * @param resolution the resolution in dpi (dots per inch)
   * 
   * @return true if the images were produced, false if there was an error
   * @throws IOException if an I/O error occurs
   */
  // public static boolean writeImage(BufferedImage image, String imageFormat, String filename,
  // int imageType, int resolution)
  // throws IOException
  // {
  // String fileName = filename + "." + imageFormat;
  // File file = new File( fileName );
  // return writeImage(image, imageFormat, file, resolution);
  // }
  //
  // /**
  // * Writes a buffered image to a file using the given image format.
  // *
  // * @param image the image to be written
  // * @param imageFormat the target format (ex. "png")
  // * @param outputStream the output stream to be used for writing
  // *
  // * @return true if the images were produced, false if there was an error
  // * @throws IOException if an I/O error occurs
  // */
  // public static boolean writeImage(BufferedImage image, String imageFormat, OutputStream outputStream)
  // throws IOException
  // {
  // return writeImage(image, imageFormat, outputStream, DEFAULT_SCREEN_RESOLUTION);
  // }

  /**
   * Writes a buffered image to a file using the given image format.
   * 
   * @param image the image to be written
   * @param imageFormat the target format (ex. "png")
   * @param outputStream the output stream to be used for writing
   * @param resolution resolution to be used when writing the image
   * 
   * @return true if the images were produced, false if there was an error
   * @throws IOException if an I/O error occurs
   */
  // public static boolean writeImage(BufferedImage image, String imageFormat, Object outputStream, int resolution)
  // throws IOException
  // {
  // return writeImage(image, imageFormat, outputStream, resolution, DEFAULT_COMPRESSION_QUALITY);
  // }

  // private static boolean addResolution(IIOMetadata meta, int resolution)
  // {
  // if (!meta.isReadOnly() && meta.isStandardMetadataFormatSupported())
  // {
  // IIOMetadataNode root = (IIOMetadataNode)meta.getAsTree(STANDARD_METADATA_FORMAT);
  // IIOMetadataNode dim = getChildNode(root, "Dimension");
  // if (dim == null)
  // {
  // dim = new IIOMetadataNode("Dimension");
  // root.appendChild(dim);
  // }
  // IIOMetadataNode child;
  // child = getChildNode(dim, "HorizontalPixelSize");
  // if (child == null)
  // {
  // child = new IIOMetadataNode("HorizontalPixelSize");
  // dim.appendChild(child);
  // }
  // child.setAttribute("value",
  // Double.toString(resolution / 25.4));
  // child = getChildNode(dim, "VerticalPixelSize");
  // if (child == null)
  // {
  // child = new IIOMetadataNode("VerticalPixelSize");
  // dim.appendChild(child);
  // }
  // child.setAttribute("value",
  // Double.toString(resolution / 25.4));
  // try
  // {
  // meta.mergeTree(STANDARD_METADATA_FORMAT, root);
  // }
  // catch (IIOInvalidTreeException e)
  // {
  // throw new RuntimeException("Cannot update image metadata: "
  // + e.getMessage());
  // }
  // return true;
  // }
  // return false;
  // }
  //
  // private static IIOMetadataNode getChildNode(Node n, String name)
  // {
  // NodeList nodes = n.getChildNodes();
  // for (int i = 0; i < nodes.getLength(); i++)
  // {
  // Node child = nodes.item(i);
  // if (name.equals(child.getNodeName()))
  // {
  // return (IIOMetadataNode)child;
  // }
  // }
  // return null;
  // }

}

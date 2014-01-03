
package com.sencko.nalb.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;

public class VirtualFileItem implements FileItem {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  FileItemHeaders headers;

  private String fieldName;

  private String contentType;

  private boolean isFormField;

  private String fileName;
  
  byte[] fileCache;
  
  

  public VirtualFileItem( String fieldName, String contentType, boolean isFormField, String fileName) {
    this.fieldName = fieldName;
    this.contentType = contentType;
    this.isFormField = isFormField;
    this.fileName = fileName;
  }

  @Override
  public FileItemHeaders getHeaders() {
    return headers;
  }

  @Override
  public void setHeaders(FileItemHeaders headers) {
    this.headers = headers;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return new ByteArrayInputStream(get());
  }

  @Override
  public String getContentType() {
    return contentType;
  }

  @Override
  public String getName() {
    return fileName;
  }

  @Override
  public boolean isInMemory() {
    return true;
  }

  @Override
  public long getSize() {
    return bos.size();
  }

  @Override
  public byte[] get() {
    if (fileCache== null){
      fileCache = bos.toByteArray(); 
    }
    return fileCache;
  }

  @Override
  public String getString(String encoding) throws UnsupportedEncodingException {
    return new String(get(), encoding);
  }

  @Override
  public String getString() {
    return new String(get());
  }

  @Override
  public void write(File file) throws Exception {
    // this is in memory!!!!
  }

  @Override
  public void delete() {
  }

  @Override
  public String getFieldName() {
    return fieldName;
  }

  @Override
  public void setFieldName(String name) {
    this.fieldName = name;
  }

  @Override
  public boolean isFormField() {
    return isFormField;
  }

  @Override
  public void setFormField(boolean state) {
    isFormField = state;
  }

  ByteArrayOutputStream bos = new ByteArrayOutputStream();

  @Override
  public OutputStream getOutputStream() throws IOException {
    bos.reset();
    fileCache = null;
    return bos;
  }
  

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return fileName + "(" + contentType + "):" + get().length;
  }

}

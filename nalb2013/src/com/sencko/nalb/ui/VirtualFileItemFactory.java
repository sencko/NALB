package com.sencko.nalb.ui;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;

public class VirtualFileItemFactory implements FileItemFactory {

  @Override
  public FileItem createItem(String fieldName, String contentType, boolean isFormField, String fileName) {
    // TODO Auto-generated method stub
    return new VirtualFileItem(fieldName, contentType, isFormField, fileName);
  }

}

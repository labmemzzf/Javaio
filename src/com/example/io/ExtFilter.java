package com.example.io;

import java.io.File;
import java.io.FileFilter;

/**
 * ExtFilter
 */
public class ExtFilter implements FileFilter{

  private String ext; 
  public ExtFilter(String ext) {
    this.ext = ext;
  }

  @Override
  public boolean accept(File pathname) {
    return !pathname.isDirectory() && pathname.getName().toLowerCase().endsWith(ext);
  }
}
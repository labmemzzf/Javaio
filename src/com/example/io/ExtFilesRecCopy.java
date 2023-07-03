package com.example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

/*
 * FilesRecCopy
 * 指定根目录下所有的指定后缀文件(包括子目录)都复制到目标目录
*/
public class ExtFilesRecCopy {

  private ExtFilter extFilter;

  public static void main(String[] args) throws Exception{
    if (args.length < 3) {
      throw new IllegalArgumentException("not enough args: please input sourcePath,copyPath,filesExt.");
    }
    File dir = new File(args[0]);            // 源目录
    File targeDir = new File(args[1]);       // 目标目录
    String filesExt = args[2].toLowerCase(); // 后缀名
    if (!filesExt.startsWith(".")) {
      filesExt = "." + filesExt;
    }

    ExtFilesRecCopy extFilesRecCopy = new ExtFilesRecCopy(filesExt);
    System.out.println("copying " + filesExt + "files from " + dir.getAbsolutePath() + " to " + targeDir.getAbsolutePath());
    extFilesRecCopy.copy(dir, targeDir);
  }

  public ExtFilesRecCopy(String fileExt) {
    this.extFilter = new ExtFilter(fileExt);
  }

  public void copy(File dir, File targeDir) throws Exception {
    if (!dir.exists()) {
      throw new IOException("dir path " + dir.getAbsolutePath() + "is not exists!"); // 根目录不存在则抛出
    }


    /*
     * !!禁止在此处创建目标目录，如果初始targeDir本身就是dir的子目录将会导致后续程序无限递归
    */

    // if (!targeDir.exists()) {
    //   targeDir.mkdirs();  // 目标目录不存在则创建
    // }

    
    File[] childDirs = dir.listFiles(new DirFilter());  //此处可优化，可以创建一个全局的过滤器，不用每次都创建
    for (File childDir : childDirs) {
      copy(childDir, new File(targeDir, childDir.getName())); // 递归调用
    }

    /*
     * !!!上下两段逻辑顺序不能改变
     * 必须先递归完子目录再复制根目录的文件，否则在targeDir本身是dir的子目录的情况下将产生无限递归
     * 原则：保证递归遍历在创建目录操作mkdirs前执行
    */

    File[] files = dir.listFiles(extFilter);
    for (File file : files) {
      bytesBuffCopy(file, new File(targeDir, file.getName())); // 字节流复制
    }
  }

  private void bytesBuffCopy(File sourceFile, File copyFile) {
    try {
      if (!copyFile.getParentFile().exists()) {
        copyFile.getParentFile().mkdirs();  // copyFile的父目录不存在则创建
      }

      if(copyFile.exists()){
        copyFile.delete();  //copyFile已存在的话先删除
      }

      byte[] bytes = new byte[1024*8];
      BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sourceFile));
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(copyFile));
      int len = bytes.length;
      while ((len = bufferedInputStream.read(bytes, 0, len)) > 0) {
        bufferedOutputStream.write(bytes, 0, len);
      }
      bufferedOutputStream.flush();
      
      bufferedInputStream.close();
      bufferedOutputStream.close();

      System.out.println(sourceFile.getAbsolutePath() + " is copied as " + copyFile.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

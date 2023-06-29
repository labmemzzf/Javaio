package com.example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/*
 * FilesCopy
 * 复制源目录下的所有指定后缀拓展名的文件到目标目录
*/
public class FilesCopy {
  public static void main(String[] args) {
    if (args.length < 3) {
      System.err.println("not enough args: please input sourcePath,copyPath,filesExt.");
    }
    File sourcePath = new File(args[0]); // 源目录
    if (!sourcePath.exists()) {
      System.err.println("sourcePath is not exit!");
    }

    File copyPath = new File(args[1]);   // 目标目录
    if (!copyPath.exists()) {
      copyPath.mkdirs();
    }

    String filesExt = args[2].toLowerCase(); // 后缀名
    if (!filesExt.startsWith(".")) {
      filesExt = "." + filesExt;
    }

    // 显示所有文件
    String[] allFilesName = sourcePath.list(null); // 所有文件及文件夹
    for (String name : allFilesName) {
      System.out.println(name);
    }
    
    // 过滤对应的后缀名文件，仅限于当前目录，不进行目录递归
    File[] targeFiles = sourcePath.listFiles(new ExtFilter(filesExt));
    System.out.println("----------下述文件将被复制----------\n");
    for (File file : targeFiles) {
      System.out.println(file.getName());
    }


    // 开始复制
    for (File file : targeFiles) {
      File copyFile = new File(copyPath, file.getName());
      if (copyFile.exists()) {
        copyFile.delete(); //若实现存在则删除
      }

      try {
        byte[] bytes = new byte[1024*8];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(copyFile));
        int len = bytes.length;
        while ((len = bufferedInputStream.read(bytes, 0, len)) > 0) {
          bufferedOutputStream.write(bytes, 0, len);
        }
        bufferedOutputStream.flush();
        
        bufferedInputStream.close();
        bufferedOutputStream.close();

        System.out.println(copyFile.getAbsolutePath() + " is copied.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
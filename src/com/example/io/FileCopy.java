package com.example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
// import java.io.FileWriter;
// import java.io.BufferedWriter;

/*
 * FileCopy
 * 文件复制
 * 从args中读取参数，分别读取为源目录、目标目录、源文件
*/
public class FileCopy {
  public static void main(String[] args) {
    if (args.length < 3) {
      System.err.println("not enough args: please input dir,copyPath,fileName.");
    }
    File rootPath = new File(args[0]);    //源文件所在根目录
    File copyPath = new File(args[1]);    //目标目录
    // File fileName = new File(args[2]);    //源文件名称

    File filePath = new File(rootPath, args[2]);
    File copyFilePath = new File(copyPath, "copy_"+args[2]);
    
    
    if (!filePath.exists() || !filePath.canRead()) {
      System.err.println("文件不存在或无法读取!");
    }

    if (!copyFilePath.getParentFile().exists()) { //拷贝目录不存在,此处这么写是为了演示如何同时创建上级目录和文件
      copyFilePath.getParentFile().mkdirs();
    } else {
      try {
        if (copyFilePath.exists()) {
          copyFilePath.delete(); // 若存在则先删除后创建
        }
        copyFilePath.createNewFile();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    try {
      FileInputStream fileInputStream = new FileInputStream(filePath); // input 输入流 读
      FileOutputStream fileOutputStream = new FileOutputStream(copyFilePath);  // output 输出流 写

      byte[] by = new byte[1024*8]; // 创建一个8k的byte容器
      int len = by.length;          // 初始时等于1024*8

      // int java.io.FileInputStream.read(byte[] b, int off, int len) throws IOException
      // 从输入流读取若干字节的数据保存到参数 b 指定的字节数组中，其中 off 是指在数组中开始保存数据位置的起始下标，len 是指读取字节的位数。返回的是实际读取的字节数，如果遇到输入流的结尾则返回 -1
      while((len = fileInputStream.read(by, 0, len)) > 0){
        //  void java.io.FileOutputStream.write(byte[] b, int off, int len) throws IOException
        // 将指定字节数组从 b 的 off 位置开始的 len 字节的内容写入输出流
        fileOutputStream.write(by, 0, len);
        fileOutputStream.flush(); //?why need?
      }
      // 已读写完成数据
      System.out.println("file copied.");
      fileInputStream.close();
      fileOutputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}

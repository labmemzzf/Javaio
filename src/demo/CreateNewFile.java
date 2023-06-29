package com.example.io.demo;

import java.io.File;
/*
 * CreateNewFile
*/
public class CreateNewFile {
  public static void main(String[] args) {
      try{
        File file = new File(".\\new\\myfile.txt");
        if (!file.getParentFile().exists()) { //用此方法先创建目录(多级)再创建文件
          file.getParentFile().mkdirs();
        }
        if(file.createNewFile())
            System.out.println("文件创建成功！");
        else
            System.out.println("出错了，该文件已经存在。");
    }
    catch(Exception e) {
        e.printStackTrace();
    }
  }
}

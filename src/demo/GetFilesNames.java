package demo;

import java.io.File;

public class GetFilesNames {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("need dir;");
    }
    File dir = new File(args[0]);
    if (!dir.exists()) {
      System.out.println("dir is not exists.");
    }

    String[] filesnameStrings = dir.list();
    for (String name : filesnameStrings) {
      System.out.println(name);
    }
    System.out.println("----------------------");

    File[] files = dir.listFiles();
    for (File file : files) {
      System.out.println(file.getName());
    }


  }
}

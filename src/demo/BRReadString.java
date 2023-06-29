package demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * BRReadString
 * 读取整行字符
*/
public class BRReadString {
  public static void main(String[] args) {
    // BufferedReader的readline()方法用于读取整行字符
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str;
    System.out.println("Enter lines of text.");
    System.out.println("Enter 'end' to quit.");
    try {
      do {
        str = br.readLine();
        System.out.println(str);
    } while (!str.equals("end"));
    } catch (Exception e) {
      System.out.println("error");
    }
  }
}

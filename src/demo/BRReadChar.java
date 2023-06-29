package demo;

import java.io.BufferedReader; // 读取单个字符用
import java.io.InputStreamReader;

/**
 * BRReadChar
 * 使用控制台(System.in)读取字符
 */
public class BRReadChar {

    public static void main(String[] args) {
      char c;
      // 使用系统输入流System.in声明BufferedReader对象
      // BufferedReader的read()方法用于读取单个字符
      // BufferedReader构造函数接受一个Reader对象
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("输入字符，按下'q'键退出。");
      try {
        do {
          c = (char)br.read();
          System.out.println(c);
        } while (c != 'q');
      } catch (Exception e) {
          System.out.println("error");
      }
    }
}

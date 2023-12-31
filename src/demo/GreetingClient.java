package demo;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class GreetingClient {
  public static void main(String[] args) {
    String serverName = "127.0.0.1";
    int port = 9999;
    if (args.length >= 2) {
      serverName = args[0];
      port = Integer.parseInt(args[1]);
    } else if (args.length == 1) {
      serverName = args[0];
    } else {

    };

    try {
      System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
      Socket client = new Socket(serverName, port);
      System.out.println("远程主机地址：" + client.getRemoteSocketAddress()); // ?
      OutputStream outToServer = client.getOutputStream(); // ?
      DataOutputStream out = new DataOutputStream(outToServer);

      out.writeUTF("Hello from" + client.getLocalSocketAddress()); // ?
      InputStream inFromServer = client.getInputStream();
      DataInputStream in = new DataInputStream(inFromServer);
      System.out.println("服务器响应： " + in.readUTF()); // ?
      client.close(); //?
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

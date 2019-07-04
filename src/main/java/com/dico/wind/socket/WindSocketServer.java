package com.dico.wind.socket;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xipeifeng
 * @since 2019-7-3
 */
public class WindSocketServer {
    public static void main(String[] args){
        try {
            // 创建服务端socket
            ServerSocket serverSocket = new ServerSocket(8080);

            // 创建客户端socket
            Socket socket = new Socket();

            //循环监听等待客户端的连接
            while(true){
                // 监听客户端
                socket = serverSocket.accept();

                ServerThread thread = new ServerThread(socket);
                thread.start();

                InetAddress address=socket.getInetAddress();
                System.out.println("当前客户端的IP："+address.getHostAddress());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}

package com.dico.wind.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xpf
 */
public class ServerSocketTest {

    public static final int PORT = 8080;
    PrintWriter pw=null;

    public static void main(String[] args) {
        System.out.println("server start...\n");
        ServerSocketTest server = new ServerSocketTest();
        server.init();
    }

    public void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {

                Socket client = serverSocket.accept();
                InetAddress address = client.getInetAddress();
                System.out.println("client:"+address.getHostAddress()+"connect");
                new HandlerThread(client);
            }
        } catch (Exception e) {
            System.out.println("server error: " + e.getMessage());
        }
    }

    private class HandlerThread implements Runnable {
        private Socket socket;
        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }
        @Override
        public void run() {
            try {

                DataInputStream input = new DataInputStream(socket.getInputStream());
                String  clientInputStr = input.readLine();
                System.out.println("send content:" + clientInputStr);


                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                pw = new PrintWriter(out);
                pw.write("{'result': 0,'data_type': 'real-time','servertime': '2019-07-03 13:43:33','nonce': 520510201}");
                pw.flush();
                out.close();
                input.close();
            } catch (Exception e) {
                System.out.println("server run error: " + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("server finally error:" + e.getMessage());
                    }
                }
            }
        }
    }

}

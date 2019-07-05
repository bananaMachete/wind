package com.dico.wind.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author xipeifeng
 * @since 2019-7-3
 */
public class SocketClient {
    public static void main(String[] args) throws InterruptedException {
        try {
            // 和服务器创建连接
            Socket socket = new Socket("localhost",8888);

            // 要发送给服务器的信息
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("{\n" +
                    "\t\"nonce\": 520510201,\n" +
                    "\t\"device_type\": \"hardware\",\n" +
                    "\t\"data_type\": \"real-time\",\n" +
                    "\t\"key\": \"0b3c9eb538152ff1ebfa1936e31b522c\",\n" +
                    "\t\"devlist\": [{\n" +
                    "\t\t\"MAC\": \"612103000001\",\n" +
                    "\t\t\"device_name\": \"\",\n" +
                    "\t\t\"device_type\": \"1\"\n" +
                    "\t}],\n" +
                    "\t\"devnewmsglist\": [{\n" +
                    "\t\t\"DevStatus\": \"0\",\n" +
                    "\t\t\"BatteryVoltage\": \"12.4\",\n" +
                    "\t\t\"Windspeed\": \"1.3\",\n" +
                    "\t\t\"Temperature\": \"22.0\",\n" +
                    "\t\t\"Windpower\": \"75\",\n" +
                    "\t\t\"RecordTime\": \"2016-10-20 10:07:03\"\n" +
                    "\t}]\n" +
                    "}");
            pw.flush();

            socket.shutdownOutput();

            // 从服务器接收的信息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info = br.readLine())!=null){
                System.out.println("我是客户端，服务器返回信息："+info);
            }

            br.close();
            is.close();
            os.close();
            pw.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

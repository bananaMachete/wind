package com.dico.wind.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author xipeifeng
 * @since 2019-7-3
 */
public class ServerThread extends Thread{
    private Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        OutputStream os=null;
        PrintWriter pw=null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String info = null;

            while((info=br.readLine())!=null){
                System.out.println("我是服务器，客户端说："+info);
            }
            socket.shutdownInput();

            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("{\n" +
                    "    \"result\": 0,\n" +
                    "    \"data_type\": \"real-time\",\n" +
                    "    \"servertime\": \"2019-07-03 13:43:33\",\n" +
                    "    \"nonce\": 520510201\n" +
                    "}");

            pw.flush();
        } catch (Exception e) {
            // TODO: handle exception
        } finally{
            //关闭资源
            try {
                if(pw!=null){
                    pw.close();
                }
                if(os!=null) {
                    os.close();
                }
                if(br!=null) {
                    br.close();
                }
                if(isr!=null) {
                    isr.close();
                }
                if(is!=null) {
                    is.close();
                }
                if(socket!=null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

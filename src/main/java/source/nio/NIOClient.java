package source.nio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author: Heiku
 * @Date: 2019/5/20
 */
public class NIOClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8081);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // 先向服务端发送数据
            os.write("Hello, Server!\0".getBytes());

            // 读取服务端发来的数据
            int b;
            while ((b = is.read()) != 0){
                System.out.print((char) b);
            }

            System.out.println();

            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

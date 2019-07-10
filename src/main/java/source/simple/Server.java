package source.simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Heiku
 * @Date: 2019/7/10
 */
public class Server {

    private ServerSocket serverSocket;

    public Server(int port){
        try {
            this.serverSocket = new ServerSocket(port);

            System.out.println("服务端启动成功，端口：" + port);
        } catch (IOException e) {
            System.out.println("服务端启动失败！");
        }
    }


    public void start(){
        new Thread(() -> {
            doStart();
        }).start();
    }


    public void doStart(){
        while (true){
            try {
                Socket socket = serverSocket.accept();

                new ClientHandler(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.heyudev.netty.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程 消耗资源大
 * java.lang.OutOfMemoryError: unable to create new native thread
 * @author supeng
 * @date 2021/06/11
 */
public class HttpServer2 {
    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8082);
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Socket socket = null;
                    try {
                        socket = serverSocket.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Handler.handle(socket);
                }
            }).start();
        }
    }
}

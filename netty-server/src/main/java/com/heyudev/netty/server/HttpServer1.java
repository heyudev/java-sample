package com.heyudev.netty.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程
 * BIO 阻塞线程
 * @author supeng
 * @date 2021/06/11
 */
public class HttpServer1 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Handler.handle(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

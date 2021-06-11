package com.heyudev.netty.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author supeng
 * @date 2021/06/11
 */
public class Handler {

    public static void handle(Socket socket){
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello,nio");
            printWriter.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}

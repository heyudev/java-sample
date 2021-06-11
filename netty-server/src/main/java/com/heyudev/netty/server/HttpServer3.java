package com.heyudev.netty.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author supeng
 * @date 2021/06/11
 */
public class HttpServer3 {
    private static final int QUEUE_MAX_SIZE = 100000;
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("http-server-pool-%d").build();
    private static ExecutorService pool = new ThreadPoolExecutor(16, 16, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(QUEUE_MAX_SIZE), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8083);
        while (true) {
            pool.execute(new Runnable() {
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
            });
        }
    }
}

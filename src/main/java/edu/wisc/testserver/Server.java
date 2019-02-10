package edu.wisc.testserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.CountDownLatch;

@Component
public class Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private final ClientHandler clientHandler;
    private final CountDownLatch shutdownLatch = new CountDownLatch(1);
    private final ServerSocket serverSocket;

    public Server(ClientHandler clientHandler) throws IOException {
        this.clientHandler = clientHandler;
        serverSocket = new ServerSocket(10000);
    }

    @PreDestroy
    public void close() throws InterruptedException, IOException {
        serverSocket.close();
        shutdownLatch.await();
    }

    public void run() throws IOException {
        LOGGER.info("Server running");

        while (shutdownLatch.getCount() > 0) {
            try {
                clientHandler.handleClient(serverSocket.accept());
            } catch (SocketException e) {
                break;
            }
        }

        shutdownLatch.countDown();

        LOGGER.info("Server shutdown");
    }

}

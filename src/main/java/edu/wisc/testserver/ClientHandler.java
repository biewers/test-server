package edu.wisc.testserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;

@Component
@EnableAsync
public class ClientHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    @Async
    public void handleClient(Socket clientSocket) throws IOException {
        LOGGER.info("Handling client " + clientSocket);
        clientSocket.close();
    }

}

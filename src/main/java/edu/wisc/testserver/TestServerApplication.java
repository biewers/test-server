package edu.wisc.testserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class TestServerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServerApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TestServerApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context = app.run(args);

        Server server = context.getBean(Server.class);
        try {
            server.run();
        } catch (IOException e) {
            LOGGER.error("Failed to run", e);
        }

        context.close();
    }

}

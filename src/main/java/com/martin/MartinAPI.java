package com.martin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class MartinAPI {
    public static void main(String[] args) {
        SpringApplication.run(MartinAPI.class, args);
        openHomePage();
    }

    private static void openHomePage() {
        String url = "http://localhost:8080/swagger-ui/";
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url).start();
            } else if (os.contains("mac")) {
                new ProcessBuilder("open", url).start();
            }
        } catch (IOException e) {
            log.error("An error occurred while trying to open the browser", e);
        }
    }
}
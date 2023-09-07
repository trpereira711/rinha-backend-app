package br.com.trpereira.rinhabackendapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RinhaBackendAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RinhaBackendAppApplication.class, args);
    }

}

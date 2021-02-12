package com.web.library.batchlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients("com.web.library.batchlibrary")
public class BatchLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchLibraryApplication.class, args);
        System.out.println("Batch start");
    }

}

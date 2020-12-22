package com.web.library.weblibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableFeignClients("com.web.library.weblibrary")
public class WebLibraryApplication {

    @RequestMapping("/")
    public String welcome(){
        return "/books";
    }

    public static void main(String[] args) {
        SpringApplication.run(WebLibraryApplication.class, args);
        System.out.println("Web-client start");
    }

}

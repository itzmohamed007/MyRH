package com.myrh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("http://localhost:4200/")
public class MyRhApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyRhApiApplication.class, args);
    }

}

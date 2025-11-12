package org.grupo14.msvcmonopatin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcMonopatinApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcMonopatinApplication.class, args);
    }

}

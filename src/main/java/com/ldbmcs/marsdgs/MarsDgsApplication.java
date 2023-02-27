package com.ldbmcs.marsdgs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ldbmcs.marsdgs.core.domain.*.repository")
public class MarsDgsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarsDgsApplication.class, args);
    }

}

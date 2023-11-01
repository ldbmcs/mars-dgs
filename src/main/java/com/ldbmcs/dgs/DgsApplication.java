package com.ldbmcs.dgs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ldbmcs.dgs.core.domain.*.repository")
public class DgsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DgsApplication.class, args);
    }

}

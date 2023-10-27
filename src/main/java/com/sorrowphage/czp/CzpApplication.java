package com.sorrowphage.czp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sorrowphage.czp.mapper")
public class CzpApplication {

    public static void main(String[] args) {
        SpringApplication.run(CzpApplication.class, args);
    }

}

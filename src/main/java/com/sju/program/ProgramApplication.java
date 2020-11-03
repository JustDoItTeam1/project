package com.sju.program;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.sju.program.mapper")
public class ProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramApplication.class, args);
	}

}

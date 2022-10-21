package com.recluseode.dockerboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.recluseode.dockerboot.repository"})
public class DockerBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerBootApplication.class, args);
	}

}

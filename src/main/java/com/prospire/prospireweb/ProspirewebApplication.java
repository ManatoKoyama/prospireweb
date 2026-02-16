package com.prospire.prospireweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.prospire.prospireweb.repository")
/**
 * Spring Boot アプリケーションのエントリポイント。
 */
public class ProspirewebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProspirewebApplication.class, args);
	}

}

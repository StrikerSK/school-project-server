package com.charts.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.charts.*")
@EnableJpaRepositories(basePackages = "com.charts.*")
@EntityScan(basePackages = "com.charts.*")
public class JavaPidApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaPidApplication.class, args);
	}

}

package br.com.stoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StoomQualificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoomQualificationApplication.class, args);
	}

}

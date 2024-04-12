package com.minpen.aceproduct;

import com.minpen.aceproduct.configuration.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class AceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(AceProductApplication.class, args);
	}

}

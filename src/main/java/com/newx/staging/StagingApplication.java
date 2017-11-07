package com.newx.staging;

import com.newx.staging.config.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DynamicDataSourceRegister.class)
public class StagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StagingApplication.class, args);
	}
}

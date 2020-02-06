package com.applause.applause;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@RequiredArgsConstructor
@PropertySource({"classpath:application.properties"})
@EnableCaching
@Slf4j
public class ApplauseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplauseApplication.class, args);
	}


}

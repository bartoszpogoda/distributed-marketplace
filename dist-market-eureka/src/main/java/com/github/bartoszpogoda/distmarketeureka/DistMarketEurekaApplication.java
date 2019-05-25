package com.github.bartoszpogoda.distmarketeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DistMarketEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistMarketEurekaApplication.class, args);
	}

}

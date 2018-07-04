package com.lcc;

import com.lcc.filter.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ServicezuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicezuulApplication.class, args);
	}

	@Bean
	public MyFilter myFilter(){
		return new MyFilter();
	}
}

package com.lcc.serviceribbon;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient //向服务中心注册
@SpringBootApplication
@ComponentScan(basePackages = "com.lcc")
@EnableHystrix//开启Hystrix(断路器)
@EnableCircuitBreaker
@EnableHystrixDashboard//开启hystrixDashboard
public class ServiceribbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceribbonApplication.class, args);
	}

	@Bean
	@LoadBalanced //表明这个restRemplate开启负载均衡的功能
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public ServletRegistrationBean getServlet(){
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;

	}
}

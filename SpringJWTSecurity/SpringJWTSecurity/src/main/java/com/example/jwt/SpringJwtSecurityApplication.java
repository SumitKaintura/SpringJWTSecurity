package com.example.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.jwt.filter.JwtFilter;

@SpringBootApplication
public class SpringJwtSecurityApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(SpringJwtSecurityApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterBean=new FilterRegistrationBean();
		filterBean.setFilter(new JwtFilter());
		filterBean.addUrlPatterns("/api/v1/*");
		return filterBean;
	}

}

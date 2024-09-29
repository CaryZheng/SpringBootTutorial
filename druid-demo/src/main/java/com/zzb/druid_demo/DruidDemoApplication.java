package com.zzb.druid_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzb.druid_demo.mapper")
public class DruidDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DruidDemoApplication.class, args);
	}

}

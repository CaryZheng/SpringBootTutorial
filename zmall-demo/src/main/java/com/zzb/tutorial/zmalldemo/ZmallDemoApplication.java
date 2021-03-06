package com.zzb.tutorial.zmalldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zzb.tutorial.zmalldemo.mapper")
public class ZmallDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZmallDemoApplication.class, args);
	}

}

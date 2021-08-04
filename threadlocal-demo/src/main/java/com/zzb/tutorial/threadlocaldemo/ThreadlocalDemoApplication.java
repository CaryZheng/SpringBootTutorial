package com.zzb.tutorial.threadlocaldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreadlocalDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadlocalDemoApplication.class, args);

		new TestThreadLocal().start();
	}

}

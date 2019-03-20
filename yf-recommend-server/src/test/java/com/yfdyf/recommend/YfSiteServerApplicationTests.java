package com.yfdyf.recommend;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootConfiguration
public class YfSiteServerApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(YfRecommendServerApplication.class, args);
	}
	@Test
	public void show(){
		System.out.println("1");
	}
}

package com.yfdyf.recommend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//开启定时任务
@EnableScheduling
@ComponentScan("com.yfdyf")
@MapperScan("com.yfdyf.recommend.dao.CommonDao")
public class YfRecommendServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(YfRecommendServerApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//单个文件最大
		factory.setMaxFileSize("50MB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("150MB");
		return factory.createMultipartConfig();
	}
}

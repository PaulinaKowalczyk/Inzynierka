package com.sentimentanalysis.sentimentanalysis;

import hello.HelloService;
import hello.HelloServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SentimentAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentimentAnalysisApplication.class, args);
	}

	@Bean(name = "helloServiceFactory")
	public HelloServiceFactory helloFactory() {
		return new HelloServiceFactory();
	}

	@Bean(name = "helloServicePython")
	public HelloService helloServicePython() throws Exception {
		return helloFactory().getObject();
	}
}

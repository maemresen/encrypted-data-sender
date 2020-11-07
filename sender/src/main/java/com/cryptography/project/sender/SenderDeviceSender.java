package com.cryptography.project.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.*;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Emre Sen
 * @date Dec 26, 2018
 * @contact maemresen07@gmail.com
 */
@SpringBootApplication
@EnableScheduling
public class
SenderDeviceSender {

	public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException {
		SpringApplication.run(SenderDeviceSender.class, args);
		System.out.println("\n SenderDevice Sender Spring Boot Application \n");
	}

}

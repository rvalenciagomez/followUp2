package com.crmfollowup;

//import javax.swing.text.html.HTML;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrMfollowUpApplication {
  
	public static void main(String[] args) {
		SpringApplication.run(CrMfollowUpApplication.class, args);
		
	}
}

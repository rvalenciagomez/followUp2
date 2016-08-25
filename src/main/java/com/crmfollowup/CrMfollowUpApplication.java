package com.crmfollowup;

//import javax.swing.text.html.HTML;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrMfollowUpApplication extends SpringBootServletInitializer {
  
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(CrMfollowUpApplication.class);
  }
  
	public static void main(String[] args) {
		SpringApplication.run(CrMfollowUpApplication.class, args);
		
	}
}

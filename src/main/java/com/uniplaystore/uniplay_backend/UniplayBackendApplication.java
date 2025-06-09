package com.uniplaystore.uniplay_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.ApplicationModule;

@SpringBootApplication
@ApplicationModule(type = ApplicationModule.Type.OPEN)
public class UniplayBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniplayBackendApplication.class, args);
	}


}

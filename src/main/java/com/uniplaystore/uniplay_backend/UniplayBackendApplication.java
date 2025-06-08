package com.uniplaystore.uniplay_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.ApplicationModule;
import org.springframework.context.annotation.Bean;
//import org.springframework.modulith.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootApplication
@ApplicationModule(type = ApplicationModule.Type.OPEN)
public class UniplayBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniplayBackendApplication.class, args);
	}

//	@Bean
//	ApplicationModules applicationModules() {
//		ApplicationModules modules = ApplicationModules.of(GameStoreApplication.class);
//		new Documenter(modules)
//				.writeDocumentation(); // Gera diagramas C4 e UML
//		return modules;
//	}

}

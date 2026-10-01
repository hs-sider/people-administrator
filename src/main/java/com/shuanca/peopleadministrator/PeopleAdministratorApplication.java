package com.shuanca.peopleadministrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

//@SpringBootApplication
//public class PeopleAdministratorApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(PeopleAdministratorApplication.class, args);
//	}
//
//}

@SpringBootApplication
public class PeopleAdministratorApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PeopleAdministratorApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PeopleAdministratorApplication.class);
    }
}

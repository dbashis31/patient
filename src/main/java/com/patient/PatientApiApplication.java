package com.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.patient.security.SpringSecurityAuditorAware;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class PatientApiApplication {

	    public static void main(String[] args) {
			SpringApplication.run(PatientApiApplication.class, args);
			
		}
	
	    // Needed for JPA to set the CreatedBy and other annotations.
		@Bean
		public AuditorAware<String> auditorAware() {
			return new SpringSecurityAuditorAware();
		}

}


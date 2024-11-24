package hr.synverso.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "hr.synverso")
@EntityScan(basePackages = {"hr.synverso"})
@ComponentScan(basePackages = {"hr.synverso"})
@EnableJpaRepositories(basePackages = "hr.synverso")
public class SynversoApp {

	public static void main(String[] args) {
		SpringApplication.run(SynversoApp.class, args);
	}

}

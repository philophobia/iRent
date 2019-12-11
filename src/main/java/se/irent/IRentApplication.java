package se.irent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@ComponentScan(basePackages = {"se.irent.dao", "se.irentcontroller", "se.irent.service"})
public class IRentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IRentApplication.class, args);
	}

}

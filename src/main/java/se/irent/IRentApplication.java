package se.irent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"se.irent.dao", "se.irent.controller", "se.irent.service", "se.irent.config"})
@EnableWebMvc
@EnableSwagger2
public class IRentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IRentApplication.class, args);
	}

}

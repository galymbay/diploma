package kz.galymbay.diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "application.yml")
public class DiplomaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiplomaApplication.class, args);
    }
}



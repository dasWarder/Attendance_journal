package by.itechart.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;


@EntityScan(basePackages = { "by.itechart.model" })
@EnableJpaRepositories(basePackages = { "by.itechart.repository" })
@SpringBootApplication(scanBasePackages = { "by.itechart.mapping",
                                            "by.itechart.service",
                                            "by.itechart.web" })
public class ApplicationWeb {

    public static void main(String[] args) {

        SpringApplication.run(ApplicationWeb.class, args);
    }

}

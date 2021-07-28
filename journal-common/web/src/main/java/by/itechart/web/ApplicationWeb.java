package by.itechart.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EntityScan(basePackages = { "by.itechart.model" })
@EnableJpaRepositories(basePackages = { "by.itechart.repository" })
@SpringBootApplication(scanBasePackages = { "by.itechart.mapping", "by.itechart.service",
                                                                        "by.itechart.web" })
public class ApplicationWeb {

    public static void main(String[] args) {

        SpringApplication.run(ApplicationWeb.class, args);
    }

}

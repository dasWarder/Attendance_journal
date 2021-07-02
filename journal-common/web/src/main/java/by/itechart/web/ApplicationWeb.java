package by.itechart.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "by.itechart.model",
                                            "by.itechart.mapping",
                                            "by.itechart.service",
                                            "by.itechart.web" } )
public class ApplicationWeb {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationWeb.class, args);
    }
}

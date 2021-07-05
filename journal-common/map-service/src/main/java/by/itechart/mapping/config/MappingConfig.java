package by.itechart.mapping.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "by.itechart.repository" })
public class MappingConfig {

}

package com.example.employeeapp;

import com.example.employeeapp.models.Employee;
import com.example.employeeapp.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository) {
        Employee e = new Employee();
        e.setName("Bob");
        e.setRole("manager");
        return args -> {
            log.info("Preloading " + employeeRepository.save(new Employee("Mark Stafki", "Dev")));
            log.info("Preloading " + employeeRepository.save(e));
        };
    }
}

package com.self.JdbcTemplateDemo;

import com.self.JdbcTemplateDemo.dto.Employee;
import com.self.JdbcTemplateDemo.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class JdbcTemplateDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JdbcTemplateDemoApplication.class, args);


        Employee e1 = context.getBean(Employee.class);
        e1.setId(11);
        e1.setName("John");
        e1.setRole("Intern");

        EmployeeRepository repo = context.getBean(EmployeeRepository.class);
        repo.save(e1);
        List<Employee> savedEmployees = repo.getAll();
        savedEmployees.forEach(System.out::println);

    }

}

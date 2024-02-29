package com.self.JdbcTemplateDemo.repository;

import com.self.JdbcTemplateDemo.dto.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private EmployeeRepository employeeRepository;

    @Test
    void testSave() {
        Employee employee = new Employee(1, "John Doe", "Developer");
        when(jdbcTemplate.update(Mockito.any(String.class), Mockito.any(Object[].class))).thenReturn(1);

        int result = employeeRepository.save(employee);

        assertEquals(1, result);
        verify(jdbcTemplate, times(1)).update(any(String.class), any(Object[].class));
    }
}
package com.self.JdbcTemplateDemo.repository;

import com.self.JdbcTemplateDemo.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Employee employee) {
        String sql = "insert into employee (id,name,role) values (?,?,?)";

        int rows = jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getRole());
        System.out.println("Total rows added are " + rows);
    }

    public List<Employee> getAll() {
        String sql = "select * from employee";

        RowMapper<Employee> rowMapper = new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setRole(rs.getString("role"));
                return e;
            }
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}

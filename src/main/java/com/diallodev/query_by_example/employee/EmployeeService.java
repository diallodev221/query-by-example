package com.diallodev.query_by_example.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.matching;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;


    // find employees matching given criteria
    public Collection<Employee> findEmployeesByExample(Employee employee) {
        Example<Employee> example = Example.of(employee);
        return repository.findAll(example);
    }

    // Find one by example
    public Optional<Employee> findOneByExample(Employee employee) {
        Example<Employee> example = Example.of(employee);
        return repository.findOne(example);
    }

    // find employees with custom matcher
    public Collection<Employee> findEmployeesWithCustomMatcher(String firstName, String department) {

        Employee employee = Employee.builder()
                .firstName(firstName)
                .department(department)
                .build();


        ExampleMatcher exampleMatcher = matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withMatcher("firstName", matcher -> matcher.exact())
                .withMatcher("department", matcher -> matcher.contains());

        Example<Employee> example = Example.of(employee, exampleMatcher);
        return repository.findAll(example);
    }

    // count employees example
    public long countEmployeesByExample(Employee employee) {
        Example<Employee> example = Example.of(employee);
        return repository.count(example);
    }

    public boolean existsByExample(Employee employee) {
        Example<Employee> example = Example.of(employee);
        return repository.exists(example);
    }
}

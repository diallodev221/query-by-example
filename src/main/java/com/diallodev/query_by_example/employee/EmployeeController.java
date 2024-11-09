package com.diallodev.query_by_example.employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/search/example")
    Collection<Employee> findEmployeesByExample(@RequestBody @Valid Employee employee) {
        return service.findEmployeesByExample(employee);
    }

    @GetMapping("/search")
    ResponseEntity<Collection<Employee>> searchEmployees(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "department", required = false) String department
    ) {

        Collection<Employee> employees = service.findEmployeesWithCustomMatcher(firstName, department);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/search/example/one")
    ResponseEntity<Employee> findOneByExample(@RequestBody @Valid Employee employee) {
        Employee employeeNotFound = service.findOneByExample(employee).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return ResponseEntity.ok(employeeNotFound);
    }

    @PostMapping("/count")
    long countEmployeesByExample(@RequestBody @Valid Employee employee) {
        return service.countEmployeesByExample(employee);
    }

    @PostMapping("/exists")
    boolean existsByExample(@RequestBody @Valid Employee employee) {
        return service.existsByExample(employee);
    }
}

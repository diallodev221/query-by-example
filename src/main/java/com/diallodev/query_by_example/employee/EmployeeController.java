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
}

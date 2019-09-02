package com.sg.hrm.controller;

import com.sg.hrm.exception.EntityNotFoundException;
import com.sg.hrm.model.Employee;
import com.sg.hrm.service.EmployeeService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EmployeeController {

        @Autowired
        private EmployeeService employeeService;

        @GetMapping("/employees")
        public List<Employee> getAllEmployees() {
                return employeeService.getAllEmployees();
        }

        @PostMapping("/employees")
        public ResponseEntity<Void> save(@Valid @RequestBody Employee employee) {
                employeeService.save(employee);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                        "/{id}").buildAndExpand(employee.getId()).toUri();
                return ResponseEntity.created(location).build();
        }

        @PutMapping("/employees")
        public ResponseEntity<Void> update(@Valid @RequestBody Employee employee) {
                employeeService.update(employee);
                return ResponseEntity.ok().build();
        }

        @GetMapping("/employees/{id}")
        public Employee findByEmployeeId(@PathVariable("id") Long id) {
                Employee e = employeeService.getEmployee(id);
                if (e == null) {
                        throw new EntityNotFoundException(id);
                }
                return e;
        }

        @DeleteMapping("/employees/{id}")
        public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
                employeeService.delete(id);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                        "/{id}").buildAndExpand(id).toUri();
                return ResponseEntity.ok().build();
        }

        @GetMapping("/employees/find/{firstname}")
        public List<Employee> find(@PathVariable("firstname") String firstName) {
                return employeeService.findByFirstName(firstName);
        }

}

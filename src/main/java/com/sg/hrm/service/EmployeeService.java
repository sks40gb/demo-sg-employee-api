package com.sg.hrm.service;

import com.sg.hrm.model.Employee;
import java.util.List;

public interface EmployeeService {


        List<Employee> findByFirstName(String firstname);

        List<Employee> getAllEmployees();

        Employee getEmployee(Long employeeId);

        Employee save(Employee employee);

        Employee update(Employee employee);
        
        boolean delete(Long id);

}

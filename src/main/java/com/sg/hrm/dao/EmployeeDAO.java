package com.sg.hrm.dao;

import com.sg.hrm.model.Employee;
import java.util.List;

/**
 *
 * @author sunsingh
 */
public interface EmployeeDAO {

    public Employee findById(Long id);

    public List<Employee> getAllEmployees();

    public List<Employee> findByFirstName(String firstName);

    public Employee save(Employee e);

    public Employee update(Employee e);
    
    public boolean delete(Long id);

}

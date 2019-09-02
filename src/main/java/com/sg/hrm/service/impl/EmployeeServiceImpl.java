package com.sg.hrm.service.impl;

import com.sg.hrm.dao.EmployeeDAO;
import com.sg.hrm.model.Employee;
import com.sg.hrm.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sunsingh
 */
@Component
public class EmployeeServiceImpl implements EmployeeService{

        
        @Autowired
        private EmployeeDAO employeeDAO;

        @Override
        public Employee getEmployee(Long employeeId) {
                return employeeDAO.findById(employeeId);
        }

        @Override
        public Employee save(Employee employee) {
                return employeeDAO.save(employee);
        }
        
        @Override
        public Employee update(Employee employee) {
                return employeeDAO.update(employee);
        }
        
        @Override
        public boolean delete(Long id) {
                return employeeDAO.delete(id);
        }

        @Override
        public List<Employee> getAllEmployees() {
                return employeeDAO.getAllEmployees();
        }

        @Override
        public List<Employee> findByFirstName(String firstname) {
                return employeeDAO.findByFirstName(firstname);
        }
}

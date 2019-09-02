package com.sg.hrm.service.impl;

import com.sg.hrm.dao.EmployeeDAO;
import com.sg.hrm.model.Employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author sunsingh
 */
public class EmployeeServiceImplTest {

        @Mock
        private EmployeeDAO dao;

        @InjectMocks
        private EmployeeServiceImpl employeeService;

        private final Employee john = new Employee(1L, "John", "John", "howtodoinjava@gmail.com");
        private final Employee alex = new Employee(2L, "Alex", "kolenchiski", "alexk@yahoo.com");
        private final Employee steve = new Employee(3L, "Steve", "Waugh", "swaugh@gmail.com");

        @Before
        public void init() {
                MockitoAnnotations.initMocks(this);
        }

        @Test
        public void findByFirstName() {
                List<Employee> expected = Arrays.asList(new Employee[]{john});
                when(dao.findByFirstName(anyString())).thenReturn(expected);
                List<Employee> searchResult = employeeService.findByFirstName("Alan");
                Assert.assertEquals(1, searchResult.size());
        }

        @Test
        public void getAllEmployeesTest() {
                List<Employee> list = new ArrayList<>();
                list.add(john);
                list.add(alex);
                list.add(steve);
                when(dao.getAllEmployees()).thenReturn(list);
                //test
                List<Employee> empList = employeeService.getAllEmployees();
                assertEquals(3, empList.size());
                verify(dao, times(1)).getAllEmployees();
        }

        @Test
        public void getEmployeeByIdTest() {
                when(dao.findById(1L)).thenReturn(john);
                Employee employee = employeeService.getEmployee(1L);
                assertEquals(john.getFirstName(), employee.getFirstName());
                assertEquals(john.getLastName(), employee.getLastName());
                assertEquals(john.getEmail(), employee.getEmail());
        }

        @Test
        public void saveEmployeeTest() {
                employeeService.save(john);
                verify(dao, times(1)).save(john);
        }

        @Test
        public void updateEmployeeTest() {
                employeeService.update(john);
                verify(dao, times(1)).update(john);
        }

}

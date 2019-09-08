package com.sg.hrm.dao.fake;

import com.sg.hrm.dao.*;
import com.sg.hrm.exception.EntityNotFoundException;
import com.sg.hrm.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author sunsingh
 */
@Component
public class FakeEmployeeDAO implements EmployeeDAO {

        private static final List<Employee> employeeList = new ArrayList<>();

        static {

                Employee john = new Employee(1L, "John", "Doe", "john@gmail.com");
                Employee alan = new Employee(2L, "Alan", "Palmore", "alan@outlook.com");

                employeeList.add(john);
                employeeList.add(alan);
//                employeeList.add(alan);
//                employeeList.add(alan);
//                employeeList.add(alan);
//                employeeList.add(alan);
//                employeeList.add(alan);
//                employeeList.add(alan);
        }

        public static Long getRandomId() {
                return System.currentTimeMillis();
        }

        @Override
        public Employee findById(Long id) {
                List<Employee> list = employeeList.stream().filter((e) -> Objects.equals(e.getId(), id)).collect(Collectors.toList());
                return list.isEmpty() ? null : list.get(0);
        }

        @Override
        public List<Employee> getAllEmployees() {
                return employeeList;
        }

        @Override
        public List<Employee> findByFirstName(String firstName) {
                return employeeList.stream().filter((e) -> e.getFirstName().equals(firstName)).collect(Collectors.toList());
        }

        @Override
        public Employee save(Employee e) {
                e.setId(getRandomId());
                this.employeeList.add(e);
                return e;
        }

        @Override
        public Employee update(Employee e) {
                int index = -1;
                for (int i = 0; i < FakeEmployeeDAO.employeeList.size(); i++) {
                        if (Objects.equals(FakeEmployeeDAO.employeeList.get(i).getId(), e.getId())) {
                                index = i;
                                break;
                        }
                }
                if (index < 0) {
                        throw new EntityNotFoundException(e.getId());
                } else {
                        this.employeeList.set(index, e);
                }
                return e;
        }

        @Override
        public boolean delete(Long id) {
                int index = -1;
                for (int i = 0; i < FakeEmployeeDAO.employeeList.size(); i++) {
                        if (Objects.equals(FakeEmployeeDAO.employeeList.get(i).getId(), id)) {
                                index = i;
                                break;
                        }
                }
                if (index < 0) {
                        throw new EntityNotFoundException(id);
                } else {
                        FakeEmployeeDAO.employeeList.remove(index);
                }
                return true;
        }

}

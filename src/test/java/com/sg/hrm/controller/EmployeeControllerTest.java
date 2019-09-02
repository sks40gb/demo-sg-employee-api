package com.sg.hrm.controller;

import com.sg.hrm.exception.EntityNotFoundException;
import com.sg.hrm.model.Employee;
import com.sg.hrm.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {

        private static final String CONTEXT_PATH = "http://localhost";

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private EmployeeService employeeService;

        Employee mockEmployee = new Employee(1L, "John", "Doe", "john@gmail.com");
        String exampleEmployeeJson = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@gmail.com\",\"gender\":\"NA\"}";

        @Test
        public void saveEmployee() throws Exception {

                Mockito.when(employeeService.save(mockEmployee)).thenReturn(mockEmployee);
                RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .post("/employees")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(exampleEmployeeJson)
                        .contentType(MediaType.APPLICATION_JSON);
                MvcResult result = mockMvc.perform(requestBuilder).andReturn();
                MockHttpServletResponse response = result.getResponse();
                assertEquals(HttpStatus.CREATED.value(), response.getStatus());
                assertEquals(CONTEXT_PATH + "/employees/1", response.getHeader(HttpHeaders.LOCATION));
        }
        
        @Test
        public void updateEmployee() throws Exception {
                Mockito.when(employeeService.update(mockEmployee)).thenReturn(mockEmployee);
                RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .put("/employees")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(exampleEmployeeJson)
                        .contentType(MediaType.APPLICATION_JSON);
                MvcResult result = mockMvc.perform(requestBuilder).andReturn();
                MockHttpServletResponse response = result.getResponse();
                assertEquals(HttpStatus.OK.value(), response.getStatus());
        }

        @Test
        public void retrieveEmployee() throws Exception {

                Mockito.when(employeeService.getEmployee(Mockito.anyLong())).thenReturn(mockEmployee);
                RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .get("/employees/1")
                        .accept(MediaType.APPLICATION_JSON);

                MvcResult result = mockMvc.perform(requestBuilder).andReturn();
                String expected = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\"}";
                MockHttpServletResponse response = result.getResponse();
                JSONAssert.assertEquals(expected, response.getContentAsString(), false);
                assertEquals(HttpStatus.OK.value(), response.getStatus());
        }

        @Test
        public void retrieveEmployeeWithNoResult() throws Exception {
                RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .get("/employees/10")
                        .accept(MediaType.APPLICATION_JSON);

                MvcResult result = mockMvc.perform(requestBuilder).andReturn();
                MockHttpServletResponse response = result.getResponse();
                assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        }

        @Test
        public void findByFirstName() throws Exception {
                List<Employee> employeeMockList = new ArrayList<>();
                employeeMockList.add(mockEmployee);
                Mockito.when(employeeService.findByFirstName(anyString())).thenReturn(employeeMockList);
                RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .get("/employees/find/Alan")
                        .accept(MediaType.APPLICATION_JSON);

                MvcResult result = mockMvc.perform(requestBuilder).andReturn();
                String expected = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\"}]";
                MockHttpServletResponse response = result.getResponse();
                JSONAssert.assertEquals(expected, response.getContentAsString(), false);
                assertEquals(HttpStatus.OK.value(), response.getStatus());
        }
        
        
        @Test
        public void deleteWithValidId() throws Exception {
                Mockito.when(employeeService.delete(anyLong())).thenReturn(true);
                RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .delete("/employees/100")
                        .accept(MediaType.APPLICATION_JSON);

                MvcResult result = mockMvc.perform(requestBuilder).andReturn();
                MockHttpServletResponse response = result.getResponse();
                assertEquals(HttpStatus.OK.value(), response.getStatus());
        }
        
        @Test
        public void deleteWithInvalidId() throws Exception {
                Mockito.when(employeeService.delete(anyLong())).thenThrow(new EntityNotFoundException(Long.MIN_VALUE));
                RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .delete("/employees/100")
                        .accept(MediaType.APPLICATION_JSON);

                MvcResult result = mockMvc.perform(requestBuilder).andReturn();
                MockHttpServletResponse response = result.getResponse();
                assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        }

}

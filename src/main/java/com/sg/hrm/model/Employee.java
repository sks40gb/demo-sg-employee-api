package com.sg.hrm.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Employee {

        private Long id;
        
        @NotNull
        @Size(min = 2, message = "First name should have atleast 2 characters")
        private String firstName;

        @NotNull
        @Size(min = 2, message = "Last name should have atleast 2 characters")
        private String lastName;

        @NotNull
        private String email;

        private Gender gender;

        public Employee() {
                this.gender = Gender.NA;
        }

        public Employee(String firstName, String lastName, String email) {
                this();
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
        }
        
          public Employee(Long id, String firstname, String lastName, String email) {
                  this(firstname, lastName, email);
                  this.id = id;
          }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public Gender getGender() {
                return gender;
        }

        public void setGender(Gender gender) {
                this.gender = gender;
        }

        @Override
        public String toString() {
                return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + '}';
        }

}

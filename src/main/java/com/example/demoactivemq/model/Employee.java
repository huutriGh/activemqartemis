package com.example.demoactivemq.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Employee implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
                + ", salary=" + salary + "]";
    }
    private float salary;

    
}

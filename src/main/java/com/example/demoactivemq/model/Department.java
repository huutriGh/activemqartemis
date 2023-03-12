package com.example.demoactivemq.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class Department implements Serializable {
    private int id;
    private String departmentName;
    private int noOfEmployee;
    @Override
    public String toString() {
        return "Department [id=" + id + ", departmentName=" + departmentName + ", noOfEmployee=" + noOfEmployee + "]";
    }
}

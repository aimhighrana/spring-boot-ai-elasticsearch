package com.ranasoftcraft.org.ai.entity;

import lombok.Data;

@Data
public class Employee {

    private String id;

    private String name;

    private Long salary;

    public Employee(String id, String name, Long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}

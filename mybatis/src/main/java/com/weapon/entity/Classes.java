package com.weapon.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Classes implements Serializable {
    private Long id;
    private String name;
    private List<Student> students;
}

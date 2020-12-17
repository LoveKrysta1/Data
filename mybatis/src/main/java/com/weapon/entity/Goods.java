package com.weapon.entity;

import lombok.Data;

import java.util.List;

@Data
public class Goods {
    private Long id;
    private String name;
    private List<Customer> customer;
}

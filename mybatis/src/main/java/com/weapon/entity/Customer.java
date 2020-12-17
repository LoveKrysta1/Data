package com.weapon.entity;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private Long id;
    private String name;
    private List<Goods> goods;
}

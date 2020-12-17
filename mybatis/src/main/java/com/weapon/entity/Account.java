package com.weapon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    public Account(Long id,String username,String password,Integer age){
        this.id =id;
        this.username=username;
        this.password=password;
        this.age=age;
    }

    private Long id;
    private String username;
    private String password;
    private Integer age;
    //用於mybatis foreach標籤
    private List<Long> ids;
}

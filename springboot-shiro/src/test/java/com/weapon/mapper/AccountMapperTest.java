package com.weapon.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@MapperScan("com.weapon.mapper")
class AccountMapperTest {

    @Autowired
    private AccountMapper mapper;

    @Test
    void select(){
        mapper.selectList(null).forEach(System.out::println);
    }

}
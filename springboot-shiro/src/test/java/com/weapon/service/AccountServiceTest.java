package com.weapon.service;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@MapperScan("com.weapon.mapper")
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void findByUsername() {
        System.out.println(accountService.findByUsername("weapon"));
    }
}
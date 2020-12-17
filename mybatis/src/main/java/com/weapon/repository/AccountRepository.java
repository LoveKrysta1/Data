package com.weapon.repository;

import com.weapon.entity.Account;

import java.util.List;

public interface AccountRepository {
    int save(Account account);
    int update(Account account);
    int deleteById(Long id);
    List<Account> findAll();
    Account findById(Long id);
    Account findByNameAndAge(String name,Integer age);
    int count();
    String findNameById(Long id);
    Account findByAccount(Account account);
    List<Account> findByIds(Account account);
}

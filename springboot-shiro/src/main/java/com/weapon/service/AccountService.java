package com.weapon.service;

import com.weapon.entity.Account;

public interface AccountService {
    public Account findByUsername(String username);
}

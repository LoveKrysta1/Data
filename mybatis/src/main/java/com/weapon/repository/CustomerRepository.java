package com.weapon.repository;

import com.weapon.entity.Customer;

public interface CustomerRepository {
    Customer findById(Long id);
}

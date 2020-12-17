package com.weapon.repository;

import com.weapon.entity.Classes;

public interface ClassesRepository {
    Classes findById(Long id);

    Classes findByIdLazy(Long id);
}

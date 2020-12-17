package com.weapon.repository;

import com.weapon.entity.Student;

public interface StudentRepository {
    Student findById(Long id);
    /**
     * 延遲加載
     */
    Student findByIdLazy(Long id);
}

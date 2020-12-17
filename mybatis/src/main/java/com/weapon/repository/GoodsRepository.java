package com.weapon.repository;

import com.weapon.entity.Goods;

public interface GoodsRepository {
    Goods findById(Long id);
}

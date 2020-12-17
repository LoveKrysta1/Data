package com.weapon.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ProductVO {
    private Integer category;
    private Integer count;
    private String description;
    private Integer userId;
    private String userName;
}

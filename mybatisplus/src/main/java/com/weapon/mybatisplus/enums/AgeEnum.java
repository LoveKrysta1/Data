package com.weapon.mybatisplus.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum AgeEnum implements IEnum<Integer> {
    ONE(1,"一歲"),
    TWO(2,"二歲"),
    THREE(3,"三歲");

    private Integer code;
    private String msg;

    AgeEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 映射接口實現枚舉
     */
    @Override
    public Integer getValue() {
        return this.code;
    }
}

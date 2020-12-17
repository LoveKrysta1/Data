package com.weapon.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.weapon.mybatisplus.enums.AgeEnum;
import com.weapon.mybatisplus.enums.StatusEnum;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user")
public class User {

    //@TableId(type = IdType.ASSIGN_ID)//(UUID String類型對應數據庫varchar)
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value= "name",select = true)
    private String name;
    private AgeEnum age;
    @TableField(exist = false)
    private String gender;
//    private Product product;

    //創建填充的處理器
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //標記樂觀鎖,通過version字段來保證數據的安全性,當修改數據的時候,會以version作為條件,當條件成立的時候才會修改成功
    @Version
    private Integer version;

    private StatusEnum status;

//    @TableLogic
    private Integer deleted;
}

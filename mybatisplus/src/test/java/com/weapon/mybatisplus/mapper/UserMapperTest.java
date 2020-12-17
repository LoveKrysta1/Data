package com.weapon.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weapon.mybatisplus.entity.ProductVO;
import com.weapon.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@MapperScan("com.weapon.mybatisplus.mapper")
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void test(){
        mapper.selectList(null).forEach(System.out::println);
    }

    //測試主鍵生成策略
    @Test
    void save(){
        User user = new User();
//        user.setAge(32);
        user.setName("rookie");
        mapper.insert(user);
    }

    @Test
    void update(){
        /**
         * 傳入entity來改
         */
//        User user = mapper.selectById(5);
//        user.setName("ken");
//        mapper.updateById(user);

        /**
         * 用wrapper
         */
        User user = mapper.selectById(2);
        user.setName("key");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("age",2);
        mapper.update(user,wrapper);
    }

    /**
     * 測試version樂觀鎖
     */
    @Test
    void update2(){
        //update ... version = 2 where version = 1
        User user = mapper.selectById(5);
        user.setName("Number1");

        //update ... version = 3 where version = 2
        User user1 = mapper.selectById(5);
        user1.setName("Number2");

        mapper.updateById(user1);
        mapper.updateById(user);
    }

    /**
     * 測試tableLogic 邏輯刪除
     */
    @Test
    void delete(){
        /**
         * 根據id刪除,但是配置了邏輯刪除,沒有物理刪除,詳細可以看sql語句
         */
//        mapper.deleteById(1);
//        mapper.deleteBatchIds(Arrays.asList(3,4));
        /**
         * 用Wrapper 條件刪除
         */
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("age",1);
//        mapper.delete(wrapper);
        /**
         * 用 map 刪除
         */
//        Map<String,Object> map = new HashMap<>();
//        map.put("id",2);
//        mapper.deleteByMap(map);
    }

    @Test
    void select(){
        QueryWrapper wrapper = new QueryWrapper();

        /**
         * 不加任何條件全部查詢
         */
        //mapper.selectList(null);

        /**
         * 單條件查詢
         */
//        wrapper.eq("name","lia");

        /**
         * 多條件查詢,丟到map集合裡邊
         */
//        Map<String,Object> map = new HashMap<>();
//        map.put("name","rookie");
//        map.put("age",1);
//        wrapper.allEq(map);

        /**
         * 大於號查詢 大於等於號查詢
         */
//        wrapper.gt("age",2);
//        wrapper.ge("age",2);

        /**
         * 不等號查詢
         */
//        wrapper.ne("name","weapon");

        /**
         * 模糊查詢  likeLeft-'%小'  likeRight-'小%'
         */
//        wrapper.like("name","小");
//        wrapper.likeLeft("name","小");

        /**
         * 聯合查詢
         */
//        wrapper.inSql("id","select id from user where id < 10");
//        wrapper.inSql("age","select age from user where age > 3");

        /**
         * 升序
         */
//        wrapper.orderByAsc("age");

        /**
         *  加條件
         */
//        wrapper.having("id>8");

        /**
         * selectByMap(map) map 只能做等值判斷,邏輯判斷需要使用 Wrapper 來處理
         */
//        Map<String,Object> map = new HashMap<>();
//        map.put("name","rookie");
//        map.put("age",1);
//        mapper.selectByMap(map);

        /**
         * 將查詢的結果集封裝到Map中
         */
//        mapper.selectMaps(wrapper).forEach(System.out::println);

        /**
         * 分頁查詢
         */
//        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<User>(1,2);
//        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> result = mapper.selectPage(page, null);
//        System.out.println(result.getSize());
//        System.out.println(result.getTotal());
//        result.getRecords().forEach(System.out::println);

        /**
         * 結果封裝成一個map集合
         */
//        Page<Map<String,Object>> page = new Page<>(1,2);
//        mapper.selectMapsPage(page,null).getRecords().forEach(System.out::println);

        /**
         *  拿出全部主鍵
         */
//        mapper.selectObjs(null).forEach(System.out::println);
        /**
         * 你的結果集只能有一個,不然報錯
         */
        mapper.selectOne(null);

//        mapper.selectList(wrapper).forEach(System.out::println))
//        System.out.println(mapper.selectList(wrapper));
    }

    @Test
    void product(){
        mapper.productList(3).forEach(System.out::println);
    }

}
package com.weapon.mobileredis.controller;


import com.weapon.mobileredis.RedisService;
import com.weapon.mobileredis.mapper.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisService service;

    @RequestMapping("/redis/setAndGet")
    @ResponseBody
    public String setAndGetValue(String name, String value) {
        redisTemplate.opsForValue().set(name, value);
        return (String) redisTemplate.opsForValue().get(name);
    }

    @RequestMapping("/redis/setAndGet2")
    @ResponseBody
    public String setAndGetValue2(String name, String value) {
        service.set("name", value);
        return (String) service.get("name");
    }
}

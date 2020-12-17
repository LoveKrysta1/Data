package com.weapon.mobileredis.controller;

import com.weapon.mobileredis.RedisService;
import com.weapon.mobileredis.service.BloomFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BloomFilterController {

    @Resource
    private BloomFilterService bloomFilterService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/bloom/idExists")
    public boolean ifExists(int id) {
        return bloomFilterService.userIdExists(id);
    }

    @RequestMapping("/bloom/redisIdAdd")
    public boolean redisIdAdd(int id) {
        return redisService.bloomFilterAdd(id);
    }

    @RequestMapping("/bloom/redisIdExists")
    public boolean redisIdExists(int id) {
        return redisService.bloomFilterExists(id);
    }
}

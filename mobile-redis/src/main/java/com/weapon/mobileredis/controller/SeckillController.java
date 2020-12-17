package com.weapon.mobileredis.controller;

import com.weapon.mobileredis.service.SeckillService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SeckillController {

    @Resource
    private SeckillService seckillService;

    @RequestMapping("/redis/seckill")
    //uid token   skuId 商品的id
    public String secKill(int uid, int skuId) {
        return seckillService.seckill(uid, skuId);
    }
}

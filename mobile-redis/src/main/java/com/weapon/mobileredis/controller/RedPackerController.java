package com.weapon.mobileredis.controller;


import com.weapon.mobileredis.RedisConfig;
import com.weapon.mobileredis.RedisService;
import com.weapon.mobileredis.domain.RedPacketInfo;
import com.weapon.mobileredis.domain.RedPacketRecord;
import com.weapon.mobileredis.mapper.RedPacketInfoMapper;
import com.weapon.mobileredis.mapper.RedPacketRecordMapper;
import com.weapon.mobileredis.service.RankingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RedPackerController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedPacketInfoMapper redPacketInfoMapper;

    @Autowired
    private RedPacketRecordMapper redPacketRecordMapper;

    private static final String TOTAL_NUM = "_totalNum";

    private static final String TOTAL_AMOUNT = "_totalAmount";


    /**
     * 添加红包
     *
     * @param uid
     * @param totalNum
     * @param totalAmount
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPacket")
    public String saveRedPacket(Integer uid, Integer totalNum, Integer totalAmount) {
        RedPacketInfo record = new RedPacketInfo();
        record.setUid(uid);
        record.setTotalPacket(totalNum);
        record.setTotalAmount(totalAmount);
        record.setCreateTime(new Date());
        record.setRemainingAmount(totalAmount);
        record.setRemainingPacket(totalNum);
        Random random = new Random();
        int a = random.nextInt(1000);
        long redPacketId = System.currentTimeMillis();//此时无法保证红包id唯一，最好是用雪花算法进行生成分布式系统唯一键
        record.setRedPacketId(redPacketId);
        redPacketInfoMapper.insert(record);
        redisService.set(redPacketId + TOTAL_NUM, totalNum + "");
        redisService.set(redPacketId + TOTAL_AMOUNT, totalAmount + "");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/getRedPacketMoney")
    public String getRedPacket(int uid, long redPacketId) {
        Integer randomAmount = 0;
        String redPacketName = redPacketId + TOTAL_NUM;
        //钱包总金额
        String totalAmountName = redPacketId + TOTAL_AMOUNT;
        String num = (String) redisService.get(redPacketName);
        if (StringUtils.isBlank(num) || Integer.parseInt(num) == 0) {
            return "抱歉！红包已经抢完了";
        }
        String totalAmount = (String) redisService.get(totalAmountName);
        if (StringUtils.isNotBlank(totalAmount)) {
            Integer totalAmountInt = Integer.parseInt(totalAmount);
            Integer totalNumInt = Integer.parseInt(num);
            Integer maxMoney = totalAmountInt / totalNumInt;
            Random random = new Random();
            randomAmount = random.nextInt(maxMoney);
        }
        // 课堂作业 lua脚本将这两个命令一起请求
        redisService.decr(redPacketName, 1);
        redisService.decr(totalAmountName, randomAmount);//redis decreby功能
        updateRacketInDB(uid, redPacketId, randomAmount);

        return randomAmount + "";
    }

    public void updateRacketInDB(int uid, long redPacketId, int amount) {
        RedPacketRecord redPacketRecord = new RedPacketRecord();
        redPacketRecord.setUid(uid);
        redPacketRecord.setRedPacketId(redPacketId);
        redPacketRecord.setAmount(amount);
        redPacketRecord.setCreateTime(new Date());
        redPacketRecordMapper.insertSelective(redPacketRecord);
        //这里应该查出RedPacketInfo的数量，将总数量和总金额减去
    }


}

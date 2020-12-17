package com.weapon.mobileredis.controller;

import com.weapon.mobileredis.RedisService;
import com.weapon.mobileredis.domain.User;
import com.weapon.mobileredis.mapper.UserMapper;
import com.weapon.mobileredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    private static final String key = "userCache_";

    @Autowired
    private UserMapper userMapper;

    @Resource
    private RedisService redisService;

    @Resource
    private UserService userService;

    @RequestMapping("getUser")
    public User getUser(String id) {
        return userMapper.find(id);
    }

    @RequestMapping("getUserCache")
    public User getUserCache(String id) {
        //先从redis里面获取值
        User user = (User) redisService.get(key + id);

        //如果拿不到则从DB取值
        if (user == null) {
            User userDB = userMapper.find(id);
            System.out.println("fresh value from DB id" + id);
            //DB非空情况刷新redis值
            if (userDB != null) {
                redisService.set(key + id, userDB);
                return userDB;
            }
        }
        return user;
    }

    @RequestMapping("/getByCache")
    @ResponseBody
    public User getByCache(String id) {
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping(value = "/getexpire", method = RequestMethod.GET)
    public User findByIdTtl(String id) {
        User u = new User();
        try {
            u = userService.findByIdTtl(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return u;
    }
}

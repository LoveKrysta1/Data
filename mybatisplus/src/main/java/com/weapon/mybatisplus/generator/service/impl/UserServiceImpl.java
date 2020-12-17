package com.weapon.mybatisplus.generator.service.impl;

import com.weapon.mybatisplus.generator.entity.User;
import com.weapon.mybatisplus.generator.mapper.UserMapper;
import com.weapon.mybatisplus.generator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weapon
 * @since 2020-12-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

package com.fashion.mjysite.service.Impl;

import com.fashion.mjysite.entity.User;
import com.fashion.mjysite.mapper.UserMapper;
import com.fashion.mjysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User selectUserByUserName(String username) {
        User user = userMapper.selectUserByUserName(username);
        return user;
    }

    @Override
    public User selectUserMapByUserName(String username) {
        return userMapper.selectUserMapByUserName(username);
    }
}

package com.fashion.mjysite.service.Impl;

import com.fashion.mjysite.entity.User;
import com.fashion.mjysite.mapper.UserMapper;
import com.fashion.mjysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User seletUserByAccount(String account) {
        User user = userMapper.seletUserByAccount(account);
        return user;
    }
}

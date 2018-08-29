package com.fashion.mjysite.service;

import com.fashion.mjysite.entity.User;

public interface UserService {
    User selectUserByUserName(String username);
    User selectUserMapByUserName(String username);
}

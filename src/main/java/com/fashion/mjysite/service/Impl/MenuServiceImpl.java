package com.fashion.mjysite.service.Impl;

import com.fashion.mjysite.entity.Menu;
import com.fashion.mjysite.entity.User;
import com.fashion.mjysite.mapper.MenuMapper;
import com.fashion.mjysite.mapper.UserMapper;
import com.fashion.mjysite.service.MenuService;
import com.fashion.mjysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByUsername(String username) {
        return menuMapper.getMenuByUsername(username);
    }
}

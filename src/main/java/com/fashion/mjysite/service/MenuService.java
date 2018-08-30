package com.fashion.mjysite.service;

import com.fashion.mjysite.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuByUsername(String username);
}

package com.fashion.mjysite.service.Impl;

import com.fashion.mjysite.entity.Article;
import com.fashion.mjysite.mapper.ArticleMapper;
import com.fashion.mjysite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleServiceIpml implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public List<Article> getArticle() {
        List<Article> articleList = articleMapper.selectAll();
        return articleList;
    }
}

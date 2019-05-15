package com.fashion.mjysite.controller;

import com.alibaba.fastjson.JSONObject;
import com.fashion.mjysite.entity.Article;
import com.fashion.mjysite.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
//    @RequiresRoles("管理员")
    @RequestMapping("/getarticle")
    @ResponseBody
    public JSONObject getArticle(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit){
        List<Article> articleList = articleService.getArticle();
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("msg", "success");
        result.put("count", 21);
        result.put("data", articleList);
        return result;
    }
    public List<Article> getArticle2(){
        List<Article> articleList = articleService.getArticle();
        return articleList;
    }
    @RequestMapping("/editarticle")
    @ResponseBody
    public String ediArticle(@RequestParam(value = "article", required = false) String article){
        //JSONObject result = new JSONObject();
        System.out.println(article);
        //result.put("res", "ok");
        return "OK";
    }
}

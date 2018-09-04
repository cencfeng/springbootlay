package com.fashion.mjysite.controller;

import com.fashion.mjysite.entity.Menu;
import com.fashion.mjysite.entity.User;
import com.fashion.mjysite.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/index")
    public String index(User user, HttpServletRequest request, HttpSession session) {
        String error = null;
        //String rememberMe = request.getParameter("rememberMe");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        //, Boolean.valueOf(rememberMe)
        token.setRememberMe(false);
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException e) {
            error = "登录密码错误.";
        } catch (ExcessiveAttemptsException e) {
            error = "登录失败次数过多";
        } catch (LockedAccountException e) {
            error = "帐号已被锁定.";
        } catch (DisabledAccountException e) {
            error = "帐号已被禁用.";
        } catch (ExpiredCredentialsException e) {
            error = "帐号已过期.";
        } catch (UnknownAccountException e) {
            error = "帐号不存在";
        } catch (UnauthorizedException e) {
            error = "您没有得到相应的授权！";
        }
       /* if(user != null){
            model.addAttribute("user",user);
            return  "menu2";
        }*/
       Map<String, Object> map = new HashMap<String, Object>();
        if(error == null){
            return "index";
        }else{
            return "403";
        }
    }
    @RequestMapping("/getMenuByUsername")
    @ResponseBody
    public List<Menu> getUserMenuByUsername(HttpSession session){
        //System.out.println("11111");
        //User user = (User)session.getAttribute("user");
        List<Menu> menuList = menuService.getMenuByUsername("20026080");
        return menuList;
    }
}

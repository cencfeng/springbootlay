package com.fashion.mjysite.controller;

import com.fashion.mjysite.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class UserController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/index")
    public String index(User user) {
        String error = "";
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
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
        System.out.println(error);
        return "index";
    }
}

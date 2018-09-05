package com.fashion.mjysite.controller;

import com.fashion.mjysite.entity.Menu;
import com.fashion.mjysite.entity.User;
import com.fashion.mjysite.service.MenuService;
import com.fashion.mjysite.util.RestResponse;
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
import java.util.List;

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
    public RestResponse index(User user, HttpServletRequest request) {
        String error = null;
        String rememberMe = request.getParameter("rememberMe");
        //, Boolean.valueOf(rememberMe)
        //token.setRememberMe(false);
        //Map<String,Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        if(session == null){
            return RestResponse.failure("session超时");
        }else{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(),Boolean.valueOf(rememberMe));
            try {
                subject.login(token);
                //map.put("url","index");
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
            System.out.println(error);
            if(error == null){
                return RestResponse.success("登录成功").setData("index");
            }else{
                return RestResponse.failure(error);
            }
        }


       /* if(user != null){
            model.addAttribute("user",user);
            return  "menu2";
        }*/
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

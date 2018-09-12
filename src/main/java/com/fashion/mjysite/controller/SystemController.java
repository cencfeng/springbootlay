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
                return RestResponse.failure("登录密码错误.").setData("login");
            } catch (ExcessiveAttemptsException e) {
                return RestResponse.failure("登录失败次数过多");
            } catch (LockedAccountException e) {
                return RestResponse.failure("帐号已被锁定.");
            } catch (DisabledAccountException e) {
                return RestResponse.failure("帐号已被禁用.");
            } catch (ExpiredCredentialsException e) {
                return RestResponse.failure("帐号已过期.");
            } catch (UnknownAccountException e) {
                return RestResponse.failure("帐号不存在");
            } catch (UnauthorizedException e) {
                return RestResponse.failure("您没有得到相应的授权！");
            }catch (Exception e){
                return RestResponse.failure("failed"+e.toString()).setData("403");
            }
            return RestResponse.success("登录成功").setData("index");

        }


       /* if(user != null){
            model.addAttribute("user",user);
            return  "menu2";
        }*/
    }
    @RequestMapping("/getMenuByUsername")
    @ResponseBody
    public List<Menu> getUserMenuByUsername() throws Exception{
        //System.out.println("11111");
        //User user = (User)session.getAttribute("user");
        User user = (User)SecurityUtils.getSubject().getPrincipal();//转换失败,spring-boot-devtools去掉,获取结果和reaml有关
//        System.out.println(user.getUsername());
        List<Menu> menuList = menuService.getMenuByUsername(user.getUsername());
        return menuList;
    }
    @RequestMapping("/changepassword")
    public String changePassword(){
        return "user/changepassword";
    }
}

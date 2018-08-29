package com.fashion.mjysite.config;
import com.fashion.mjysite.entity.*;
import com.fashion.mjysite.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyShiroRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService ;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = new User();
        try{
            user = userService.selectUserMapByUserName((String)principals.getPrimaryPrincipal());

        }catch (Exception e){
            System.out.println(e.toString());
        }
        for(Role role:user.getRoleList()){
            authorizationInfo.addRole(role.getRolename());
        }
        for(Menu menu:user.getMenuList()){
            authorizationInfo.addStringPermission(menu.getPermission());
        }
        System.out.println(authorizationInfo.getStringPermissions());
        System.out.println(authorizationInfo.getRoles());
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("认证MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        User user = userService.selectUserByUserName(username);
        User user = userService.selectUserMapByUserName(username);
        if(user == null){
            return null;
        }
        //加密方式
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),//salt=username+salt
                getName()  //realm name
        );
        //明文验证
       /* SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                getName()  //realm name
        );*/
        return authenticationInfo;
    }
}


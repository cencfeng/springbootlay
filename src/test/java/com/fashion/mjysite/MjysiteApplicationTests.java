package com.fashion.mjysite;

import com.fashion.mjysite.entity.Menu;
import com.fashion.mjysite.entity.User;
import com.fashion.mjysite.service.MenuService;
import com.fashion.mjysite.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MjysiteApplicationTests {
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Test
	public void contextLoads() {
	}
	@Test
	public void seletUserByUsername(){
		User user = userService.selectUserByUserName("20026080");
		System.out.println(user.getUsername());
		User user2 = userService.selectUserMapByUserName("20026080");
		System.out.println(user2.getUsername());
	}
	@Test
	public void md5jiami(){
		String hashAlgorithmName = "MD5";
		Object mima = "123456";
		int hashIterations = 2;
		ByteSource salt = ByteSource.Util.bytes("chengfeng");
		String result = new SimpleHash(hashAlgorithmName, mima, salt, hashIterations).toBase64();
		System.out.println(result);
	}
	@Test
	public void getMenuByUsernameTest(){
		List<Menu> menuList = menuService.getMenuByUsername("20026080");
		for (Menu menu : menuList){
			System.out.println(menu.getMenuname()+"主菜单");
			for (Menu menu1 : menu.getChildren()){
				System.out.println(menu1.getMenuname()+"子菜单");
			}
		}
	}
	@Test
	public void getRedisTest(){
		String username = stringRedisTemplate.opsForValue().get("username");
		stringRedisTemplate.opsForValue().set("love","xiaoting");
		System.out.println(stringRedisTemplate.opsForValue().get("love"));
		System.out.println(username);

	}
	@Test
    public void setRedisObject(){
	    User user = new User();
	    user.setUsername("cen");
	    user.setNickname("chengfeng");
	    redisTemplate.opsForValue().set("objecttest",user);
    }
    @Test
	public void getShiroSession(){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("user");
		System.out.println(user.getUsername());
	}

}

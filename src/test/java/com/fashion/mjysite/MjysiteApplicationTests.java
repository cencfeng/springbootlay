package com.fashion.mjysite;

import com.fashion.mjysite.entity.User;
import com.fashion.mjysite.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MjysiteApplicationTests {
	@Autowired
	private UserService userService;

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
		Object mima = "666666";
		int hashIterations = 2;
		ByteSource salt = ByteSource.Util.bytes("chengfeng");
		String result = new SimpleHash(hashAlgorithmName, mima, salt, hashIterations).toBase64();
		System.out.println(result);
	}

}

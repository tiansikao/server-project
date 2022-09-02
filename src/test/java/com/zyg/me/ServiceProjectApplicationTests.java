package com.zyg.me;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Optional;
import com.google.common.base.Preconditions;
import com.zyg.me.commons.MD5Utils;
import com.zyg.me.domain.mapper.UserMapper;
import com.zyg.me.domain.mapper.UserRolesMapper;
import com.zyg.me.domain.model.User;
import com.zyg.me.domain.model.UserRoles;
import com.zyg.me.domain.model.UserRolesExample;
import com.zyg.me.service.Impl.UserServiceImpl;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.OpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceProjectApplicationTests {

	@Autowired
	UserMapper userMapper;

	@Test
	public void contextLoads( ) throws Exception {
        String jsonStr = "我Hw8A我啊";
        String jsonStr1 = "asd";
        String jsonStr2 = "1我我啊1";

		System.out.println(
				Base64.getEncoder().encodeToString(jsonStr.getBytes())
		);
		System.out.println(
				Base64.getEncoder().encodeToString(jsonStr1.getBytes())
		);


		String resultJson = "5oiR5oiR5ZWKMQ==";
		String aa = new String(Base64.getDecoder().decode(resultJson.getBytes()),"utf-8");
		System.out.println(aa);
	}

    @Test
    public void contextLoad2() throws Exception {
        String jsonStr = "a1a";
        String jsonStr1 = "bb";

    }
}

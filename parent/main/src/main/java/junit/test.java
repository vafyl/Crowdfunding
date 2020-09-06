package junit;

import com.study.zh.bean.TUser;
import com.study.zh.manager.service.UserService;
import com.study.zh.util.MD5Util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//数据库添加数据
public class test {

    public static void main(String[] args) {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring*xml");
        UserService userService = ioc.getBean(UserService.class);
        for (int i = 1;i <= 100;i++){
            TUser user = new TUser();
            user.setLoginacct("test"+i);
            user.setUserpswd(MD5Util.digest("zhanghuan123"));
            user.setUsername("test"+i);
            user.setEmail("test"+i+"@qq.com");
            user.setCreatetime("2019-12-28 10:30:00");
            userService.insertUser(user);
        }
    }
}

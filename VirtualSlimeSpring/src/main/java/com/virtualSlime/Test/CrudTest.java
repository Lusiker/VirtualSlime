package com.virtualSlime.Test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrudTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void userTest(){
        User u = new User("1","2","3");
        userMapper.insert(u);
        System.out.println(userMapper.selectById(u.getUid()));
        userMapper.selectList(new QueryWrapper<User>().eq("user_name", 222)).forEach(System.out::println);
    }


}

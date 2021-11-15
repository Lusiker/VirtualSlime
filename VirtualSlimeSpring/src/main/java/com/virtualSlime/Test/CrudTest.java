package com.virtualSlime.Test;

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
        u.setUserName("222");
        userMapper.updateById(u);
        System.out.println(userMapper.selectById(u.getUid()));
    }


}

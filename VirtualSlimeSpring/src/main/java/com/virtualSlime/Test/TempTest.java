package com.virtualSlime.Test;

import com.virtualSlime.Entity.User;
import com.virtualSlime.Mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TempTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void printOut(){
        userMapper.insert(new User(null, "yuuNa","@gmail","123456"));
    }
}

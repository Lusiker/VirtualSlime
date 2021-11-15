package com.virtualslime.test;

import com.virtualslime.Entity.Admin;
import com.virtualslime.Mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TempTest {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void printOut(){
        adminMapper.insert(new Admin("chenRui", (byte) 1));
    }
}

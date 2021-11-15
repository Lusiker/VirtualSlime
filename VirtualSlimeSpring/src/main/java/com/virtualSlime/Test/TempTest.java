package com.virtualSlime.Test;

import com.virtualSlime.Entity.Admin;
import com.virtualSlime.Mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TempTest {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void printOut(){
        adminMapper.insert(new Admin("xuYi", (byte) 1));
    }
}

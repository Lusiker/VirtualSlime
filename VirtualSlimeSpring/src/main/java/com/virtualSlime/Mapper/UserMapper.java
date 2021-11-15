package com.virtualSlime.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.virtualSlime.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

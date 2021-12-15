package com.virtualSlime.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.virtualSlime.Entity.Relation.UserCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCartMapper extends BaseMapper<UserCart> {
}

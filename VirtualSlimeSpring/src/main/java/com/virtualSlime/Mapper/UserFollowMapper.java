package com.virtualSlime.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.virtualSlime.Entity.Relation.UserFollow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFollowMapper extends BaseMapper<UserFollow> {
}

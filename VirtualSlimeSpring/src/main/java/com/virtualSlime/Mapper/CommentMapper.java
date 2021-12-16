package com.virtualSlime.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.virtualSlime.Entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}

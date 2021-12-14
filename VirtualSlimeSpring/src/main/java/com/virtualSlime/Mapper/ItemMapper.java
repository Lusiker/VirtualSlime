package com.virtualSlime.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.virtualSlime.Entity.Item;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper extends BaseMapper<Item> {
}

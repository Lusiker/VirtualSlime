package com.virtualSlime.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Enum.ItemState;
import com.virtualSlime.Mapper.ItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemRepository {
    @Resource
    ItemMapper itemMapper;

    //basic insert
    public boolean insertItem(Item item){
        return itemMapper.insert(item) == 1;
    }

    //basic update
    public boolean updateItem(Item item){
        UpdateWrapper<Item> wrapper = new UpdateWrapper<Item>();
        wrapper.eq("iid",item.getUid());

        return itemMapper.update(item, wrapper) == 1;
    }

    //select methods
    public List<Item> selectAllItems(){
        return itemMapper.selectList(null);
    }

    public List<Item> selectAllAvailableItems(){
        //select all items that are not hidden
        QueryWrapper<Item> wrapper = new QueryWrapper<Item>().ne("item_state", ItemState.HIDDEN);

        return itemMapper.selectList(wrapper);
    }

    public List<Item> selectItemsByUid(int uid){
        //select items of a given user
        QueryWrapper<Item> wrapper = new QueryWrapper<Item>().eq("uid",uid);

        return itemMapper.selectList(wrapper);
    }

    public List<Item> selectItemsByCid(int cid){
        //select items of a given category id
        QueryWrapper<Item> wrapper = new QueryWrapper<Item>().eq("cid",cid);

        return itemMapper.selectList(wrapper);
    }
}

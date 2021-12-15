package com.virtualSlime.Utils;

import com.virtualSlime.Entity.Category;
import com.virtualSlime.Mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class GlobalCategoryCache {
    private final HashMap<Short, Category> map = new HashMap<Short,Category>();

    public GlobalCategoryCache(@Autowired CategoryMapper categoryMapper){
        List<Category> list = categoryMapper.selectList(null);
        for(Category cat : list){
            map.put(cat.getCid(),cat);
        }
    }

    public String getCategoryNameFromCid(short cid){
        Category cat = map.get(cid);
        if(cat == null){
            return "undefined";
        }

        return cat.getName();
    }
}

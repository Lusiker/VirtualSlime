package com.virtualSlime.Utils;

import com.virtualSlime.Entity.Category;
import com.virtualSlime.Mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a cache that stores all "category id - category name" entries
 * to avoid accessing the DB too often.
 */
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

    public List<Category> getAllCategoryInfo(){
        List<Category> result = new ArrayList<Category>();
        for(Map.Entry<Short, Category> e : map.entrySet()){
            result.add(e.getValue());
        }

        return result;
    }
}

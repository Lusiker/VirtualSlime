package com.virtualSlime.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.Relation.UserBought;
import com.virtualSlime.Entity.Relation.UserCart;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.ItemState;
import com.virtualSlime.Mapper.ItemMapper;
import com.virtualSlime.Mapper.UserBoughtMapper;
import com.virtualSlime.Mapper.UserCartMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemRepository {
    @Resource
    ItemMapper itemMapper;
    @Resource
    UserCartMapper userCartMapper;
    @Resource
    UserBoughtMapper userBoughtMapper;

    //basic insert
    public boolean insertItem(Item item){
        return itemMapper.insert(item) == 1;
    }

    public boolean insertItemToCart(User user, Item item){
        //insert a new record to cart
        UserCart record = new UserCart(user.getUid(),item.getIid());

        return userCartMapper.insert(record) == 1;
    }

    public boolean insertItemToBought(User user, Item item, Date now){
        //insert a record to Bought

        UserBought record = new UserBought(user.getUid(),item.getIid(),now);

        return userBoughtMapper.insert(record) == 1;
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

    public List<Item> selectItemsByUid(User user){
        //select items of a given user
        QueryWrapper<Item> wrapper = new QueryWrapper<Item>().eq("uid",user.getUid());

        return itemMapper.selectList(wrapper);
    }

    public List<Item> selectItemsByCid(int cid){
        //select items of a given category id
        QueryWrapper<Item> wrapper = new QueryWrapper<Item>().eq("cid",cid);

        return itemMapper.selectList(wrapper);
    }

    public List<UserCart> selectUserCart(User user){
        //select all items in user's cart
        QueryWrapper<UserCart> wrapper = new QueryWrapper<UserCart>().eq("uid",user.getUid());

        return userCartMapper.selectList(wrapper);
    }

    public List<Item> selectUserCartAsItemList(User user){
        List<UserCart> cart = selectUserCart(user);

        List<Item> result = new ArrayList<Item>();
        for(UserCart i : cart){
            Item newItem = selectItemByIid(i.getIid());
            result.add(newItem);
        }

        return result;
    }

    public List<UserBought> selectUserBought(User user){
        //select all items a user has bought
        QueryWrapper<UserBought> wrapper = new QueryWrapper<UserBought>().eq("uid",user.getUid());

        return userBoughtMapper.selectList(wrapper);
    }

    public List<Item> selectUserBoughtAsItemList(User user){
        List<UserBought> bought = selectUserBought(user);

        List<Item> result = new ArrayList<Item>();
        for(UserBought b : bought){
            Item newItem = selectItemByIid(b.getIid());
            result.add(newItem);
        }

        return result;
    }

    public Item selectItemByIid(int iid){
        //select a particular item
        QueryWrapper<Item> wrapper = new QueryWrapper<Item>().eq("iid",iid);

        Item item = itemMapper.selectOne(wrapper);

        if(item == null){
            return null;
        }else if(item.getItemState() != ItemState.HIDDEN){
            return item;
        }else {
            return null;
        }
    }

    public int getCartSize(int uid){
        QueryWrapper<UserCart> wrapper = new QueryWrapper<UserCart>().eq("uid",uid);
        try {
            return Math.toIntExact(userCartMapper.selectCount(wrapper));
        }catch (Exception e){
            return -1;
        }
    }

    public boolean checkHasBoughtItem(User user,Item item){
        List<UserBought> bought = selectUserBought(user);
        if(bought.size() == 0){
            return false;
        }
        
        for(UserBought i : bought){
            if(i.getIid().equals(item.getIid())){
                return true;
            }
        }

        return false;
    }

    public boolean checkHasInCart(User user,Item item){
        List<UserCart> cart = selectUserCart(user);

        if(cart.size() == 0){
            return false;
        }

        for(UserCart i : cart){
            if(i.getIid().equals(item.getIid())){
                return true;
            }
        }

        return false;
    }

    //update methods
    public boolean updateItemVisit(Item item){
        //+1 to item visit count
        int previous_visit = item.getVisitCount();
        item.setVisitCount(previous_visit + 1);

        return updateItem(item);
    }
}

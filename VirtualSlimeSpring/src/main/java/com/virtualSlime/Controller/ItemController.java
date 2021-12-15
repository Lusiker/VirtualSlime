package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.Relation.UserCart;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.ItemPageState;
import com.virtualSlime.Enum.ItemState;
import com.virtualSlime.Service.ItemRepository;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.GlobalCategoryCache;
import com.virtualSlime.Utils.ItemInfoWrapper;
import com.virtualSlime.Utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class ItemController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private ItemRepository itemRepository;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private GlobalCategoryCache categoryCache;


    @RequestMapping("/item/{iid}")
    public String itemByIid(@PathVariable(value = "iid")String newIid) throws JsonProcessingException {
        int iid;
        try{
            iid = Integer.parseInt(newIid);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Wrong Info:" + newIid));
        }

        Item item = itemRepository.selectItemByIid(iid);
        if(item == null){
            //item not exist or hidden
            return objectMapper.writeValueAsString(new Result(ItemPageState.ITEM_NOT_EXIST,newIid));
        }

        User user = userRepository.selectUserByUid(item.getUid());
        ItemInfoWrapper itemInfoWrapper = new ItemInfoWrapper(item,user,categoryCache);

        return objectMapper.writeValueAsString(new Result(ItemPageState.SUCCESSFUL,itemInfoWrapper));
    }

    /**
     * @param newIid iid
     * @param newUid uid of target cart
     * @return result
     */
    @RequestMapping("item/{iid}/toCart={uid}")
    public String addItemToUserCart(@PathVariable(value = "iid")String newIid,
                                    @PathVariable(value = "uid")String newUid) throws JsonProcessingException {
        int iid;
        try{
            iid = Integer.parseInt(newIid);
        }catch (Exception e){
            //iid bad input
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Wrong Info:" + newIid));
        }

        Item item = itemRepository.selectItemByIid(iid);
        if(item == null){
            //item not exist or hidden
            return objectMapper.writeValueAsString(new Result(ItemPageState.ITEM_NOT_EXIST,newIid));
        }

        int uid;
        try{
            uid = Integer.parseInt(newUid);
        }catch (Exception e){
            //uid bad input
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Wrong Info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ItemPageState.INTERNAL_ERROR,null));
        }

        if(itemRepository.checkHasInCart(user,item)){
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Has Added"));
        }

        if(itemRepository.insertItemToCart(user,item)){
            //successfully added item to cart
            //return (4-add_successful), uid-iid
            return objectMapper.writeValueAsString(new Result(ItemPageState.ADD_SUCCESSFUL,uid + "-" + iid));
        }else{
            //write failed, possibly won't happen
            return objectMapper.writeValueAsString(new Result(ItemPageState.INTERNAL_ERROR,null));
        }
    }

    @RequestMapping("/item/{iid}/buy={uid}")
    public String userBuyItem(@PathVariable(value = "iid")String newIid,
                              @PathVariable(value = "uid")String newUid) throws JsonProcessingException{
        int iid;
        try{
            iid = Integer.parseInt(newIid);
        }catch (Exception e){
            //iid bad input
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Wrong Info:" + newIid));
        }

        Item item = itemRepository.selectItemByIid(iid);
        if(item == null){
            //item not exist or hidden
            return objectMapper.writeValueAsString(new Result(ItemPageState.ITEM_NOT_EXIST,newIid));
        }

        int uid;
        try{
            uid = Integer.parseInt(newUid);
        }catch (Exception e){
            //uid bad input
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Wrong Info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ItemPageState.INTERNAL_ERROR,null));
        }

        if(itemRepository.checkHasBoughtItem(user,item)){
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Has Bought"));
        }

        int buyResult = userRepository.updateUserCurrency(user,item);
        switch (buyResult){
            case -1 -> {
                BigDecimal gap = item.getItemPrice().add(item.getItemPriceDiscounted()).subtract(user.getUserCurrency());
                return objectMapper.writeValueAsString(new Result(ItemPageState.NOT_ENOUGH_CURRENCY,gap));
            }
            case 1 -> {
                Date now = new Date();
                if(itemRepository.insertItemToBought(user,item,now)) {
                    return objectMapper.writeValueAsString(new Result(ItemPageState.BUY_SUCCESSFUL, user.getUserCurrency()));
                }

                return objectMapper.writeValueAsString(new Result(ItemPageState.INTERNAL_ERROR,null));
            }
            default -> {
                return objectMapper.writeValueAsString(new Result(ItemPageState.INTERNAL_ERROR,null));
            }
        }
    }
}
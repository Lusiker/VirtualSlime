package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.PageState.ItemPageState;
import com.virtualSlime.Service.ItemRepository;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.GlobalCategoryCache;
import com.virtualSlime.Utils.InfoWrapper.ItemInfoWrapper;
import com.virtualSlime.Utils.InfoWrapper.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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
     * @param newUid uid
     * @param newItemName String no longer than 50
     * @param newItemBrief String no longer than 200
     * @param newItemPrice String of Decimal(12,2)
     * @param newCid cid of an existing category
     * @return create result
     */
    @RequestMapping("/item/create")
    public String createItem(@RequestParam(value = "uid",defaultValue = "")String newUid,
                             @RequestParam(value = "itemName",defaultValue = "")String newItemName,
                             @RequestParam(value = "itemBrief",defaultValue = "")String newItemBrief,
                             @RequestParam(value = "itemPrice",defaultValue = "0.0")String newItemPrice,
                             @RequestParam(value = "cid",defaultValue = "1")String newCid) throws JsonProcessingException{
        int uid;
        try{
            uid = Integer.parseInt(newUid);
        }catch (Exception e){
            //uid bad input
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Wrong Info:" + newUid));
        }

        short cid;
        try{
            cid = Short.parseShort(newCid);
        }catch (Exception e){
            //cid bad input
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Wrong Info:" + newCid));
        }

        if(categoryCache.getCategoryNameFromCid(cid).equals("undefined")){
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Category Not Exist:" + newCid));
        }

        if(newItemName.length() > 50){
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Item Name Too Long"));
        }

        if(newItemBrief.length() > 200){
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Item Brief Too Long"));
        }

        BigDecimal itemPrice;
        try{
            itemPrice = new BigDecimal(newItemPrice);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Wrong Info:" + newItemPrice));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ItemPageState.INTERNAL_ERROR,null));
        }

        Item newItem = new Item(user.getUid(),newItemName,newItemBrief,itemPrice,cid);
        if(!itemRepository.insertItem(newItem)){
            return objectMapper.writeValueAsString(new Result(ItemPageState.INTERNAL_ERROR,null));
        }

        return objectMapper.writeValueAsString(new Result(ItemPageState.CREATE_SUCCESSFUL,newItem.getIid()));
    }

    /**
     * @param newIid iid
     * @param newUid uid of target cart owner
     * @return result
     */
    @RequestMapping("item/{iid}/toCart")
    public String addItemToUserCart(@PathVariable(value = "iid")String newIid,
                                    @RequestParam(value = "uid",defaultValue = "")String newUid) throws JsonProcessingException {
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

        if(!user.getUserHasActivated()){
            //not activated
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Not Activated"));
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

    /**
     * @param newIid iid
     * @param newUid uid of the buyer
     * @return buy result
     */
    @RequestMapping("/item/{iid}/buy")
    public String userBuyItem(@PathVariable(value = "iid")String newIid,
                              @RequestParam(value = "uid",defaultValue = "")String newUid) throws JsonProcessingException{
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

        if(!user.getUserHasActivated()){
            //not activated
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Not Activated"));
        }

        if(itemRepository.checkHasBoughtItem(user,item)){
            return objectMapper.writeValueAsString(new Result(ItemPageState.FAIL,"Has Bought"));
        }

        int buyResult = userRepository.updateUserCurrencyBuy(user,item);
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

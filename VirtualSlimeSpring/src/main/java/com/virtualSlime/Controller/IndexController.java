package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.Category;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.PageState.IndexPageState;
import com.virtualSlime.Enum.PageState.ProfilePageState;
import com.virtualSlime.Service.ItemRepository;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.GlobalCategoryCache;
import com.virtualSlime.Utils.InfoWrapper.ItemInfoWrapper;
import com.virtualSlime.Utils.InfoWrapper.Result;
import com.virtualSlime.Utils.InfoWrapper.UserInfoWrapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ItemRepository itemRepository;
    @Resource
    private GlobalCategoryCache categoryCache;

    private List<ItemInfoWrapper> getItemInfoWrapperList(List<Item> list){
        List<ItemInfoWrapper> processedItems = new ArrayList<>();

        for(Item i : list){
            User user = userRepository.selectUserByUid(i.getUid());
            ItemInfoWrapper wrapper = new ItemInfoWrapper.ItemInfoWrapperBuilder()
                    .setItem(i,categoryCache)
                    .setSeller(user)
                    .build();

            processedItems.add(wrapper);
        }

        return processedItems;
    }

    private List<UserInfoWrapper> getUserInfoWrapperList(List<User> list){
        List<UserInfoWrapper> processedItems = new ArrayList<>();

        for(User u : list){
            UserInfoWrapper wrapper = new UserInfoWrapper.UserInfoWrapperBuilder()
                    .setLesserInfo(u)
                    .build();

            processedItems.add(wrapper);
        }

        return processedItems;
    }

    private int parsePageString(String page){
        if(page.equals("")){
            return 1;
        }else{
            int result;
            try{
                result = Integer.parseInt(page);
                if(result <= 0){
                    return 1;
                }

                return result;
            }catch (Exception e){
                return 1;
            }
        }
    }

    @RequestMapping("/home")
    public String index(@RequestParam(value = "page",defaultValue = "")String newPage) throws JsonProcessingException {
        int page = parsePageString(newPage);

        List<Item> items = itemRepository.selectAvailableItemsByPage(page,10);
        List<ItemInfoWrapper> processedItems = getItemInfoWrapperList(items);

        if(processedItems.size() != 0) {
            return objectMapper.writeValueAsString(new Result(IndexPageState.SUCCESSFUL,processedItems));
        }

        return objectMapper.writeValueAsString(new Result(IndexPageState.NO_MORE,null));
    }

    @RequestMapping("/home/cid={cid}")
    public String indexWithCategory(@PathVariable(value = "cid")String newCid,
                                    @RequestParam(value = "page")String newPage) throws JsonProcessingException{
        int page = parsePageString(newPage);

        int cid;
        try{
            cid = Integer.parseInt(newCid);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED, newCid));
        }

        List<Item> items = itemRepository.selectAvailableItemsByPageWithCid(page,10,cid);
        List<ItemInfoWrapper> processedItems = getItemInfoWrapperList(items);

        if(processedItems.size() != 0) {
            return objectMapper.writeValueAsString(new Result(IndexPageState.SUCCESSFUL,processedItems));
        }

        return objectMapper.writeValueAsString(new Result(IndexPageState.NO_MORE,null));
    }

    @RequestMapping("/search/item")
    public String searchUserSimple(@RequestParam(value = "keyword",defaultValue = "")String newKeyword,
                                   @RequestParam(value = "page",defaultValue = "")String newPage) throws JsonProcessingException{
        int page = parsePageString(newPage);
        List<Item> items = itemRepository.selectAvailableItemsLikeKeywordByPage(newKeyword,page,10);
        List<ItemInfoWrapper> processedItems = getItemInfoWrapperList(items);

        if(processedItems.size() != 0) {
            return objectMapper.writeValueAsString(new Result(IndexPageState.SUCCESSFUL,processedItems));
        }

        return objectMapper.writeValueAsString(new Result(IndexPageState.NO_MORE,null));
    }

    @RequestMapping("/search/user")
    public String searchItemSimple(@RequestParam(value = "keyword",defaultValue = "")String newKeyword,
                                   @RequestParam(value = "page",defaultValue = "")String newPage) throws JsonProcessingException{
        int page = parsePageString(newPage);
        List<User> users = userRepository.selectAvailableUsersLikeKeywordByPage(newKeyword,page,10);
        List<UserInfoWrapper> processedUsers = getUserInfoWrapperList(users);

        if(processedUsers.size() != 0) {
            return objectMapper.writeValueAsString(new Result(IndexPageState.SUCCESSFUL,processedUsers));
        }

        return objectMapper.writeValueAsString(new Result(IndexPageState.NO_MORE,null));
    }

    /**
     * acquire all category info from this api
     * @return list of catId - catName
     */
    @RequestMapping("/categories")
    public String categories() throws JsonProcessingException{
        List<Category> categories = categoryCache.getAllCategoryInfo();

        return objectMapper.writeValueAsString(new Result(IndexPageState.SUCCESSFUL,categories));
    }
}
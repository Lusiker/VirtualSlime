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

    @RequestMapping("/home")
    public String index(@RequestParam(value = "page",defaultValue = "")String newPage) throws JsonProcessingException {
        int page;
        if(newPage.equals("")){
            page = 1;
        }else{
            try{
                page = Integer.parseInt(newPage);
                if(page <= 0){
                    page = 1;
                }
            }catch (Exception e){
                return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED, newPage));
            }
        }

        List<Item> items = itemRepository.selectAvailableItemsByPage(page,10);
        List<ItemInfoWrapper> processedItems = getItemInfoWrapperList(items);

        if(processedItems.size() != 0) {
            return objectMapper.writeValueAsString(new Result(IndexPageState.SUCCESSFUL,processedItems));
        }

        return objectMapper.writeValueAsString(new Result(IndexPageState.FAILED,null));
    }

    @RequestMapping("/home/cid={cid}")
    public String indexWithCategory(@PathVariable(value = "cid")String newCid,
                                    @RequestParam(value = "page")String newPage) throws JsonProcessingException{
        int page;
        if(newPage.equals("")){
            page = 1;
        }else{
            try{
                page = Integer.parseInt(newPage);
                if(page <= 0){
                    page = 1;
                }
            }catch (Exception e){
                return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED, newPage));
            }
        }

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

        return objectMapper.writeValueAsString(new Result(IndexPageState.FAILED,null));
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
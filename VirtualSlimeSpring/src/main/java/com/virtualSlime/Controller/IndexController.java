package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Enum.IndexPageState;
import com.virtualSlime.Service.ItemRepository;
import com.virtualSlime.Utils.GlobalCategoryCache;
import com.virtualSlime.Utils.ItemInfoWrapper;
import com.virtualSlime.Utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private ItemRepository itemRepository;
    @Resource
    private GlobalCategoryCache categoryCache;

    @RequestMapping("/")
    public String index() throws JsonProcessingException {
        List<Item> items = itemRepository.selectAllAvailableItems();
        List<ItemInfoWrapper> processedItems = new ArrayList<ItemInfoWrapper>();

        for(Item i : items){
            ItemInfoWrapper wrapper = new ItemInfoWrapper(i,categoryCache);
            processedItems.add(wrapper);
        }

        return objectMapper.writeValueAsString(new Result(IndexPageState.SUCCESSFUL,processedItems));
    }
}
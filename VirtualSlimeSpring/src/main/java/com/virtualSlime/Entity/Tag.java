package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(schema = "virtual_slime", value = "item_tag")
public class Tag {
    @TableId(type = IdType.AUTO)
    private final Integer tid;
    private String tagName;

    public Tag(Integer tid, String tagName) {
        this.tid = tid;
        this.tagName = tagName;
    }

    public Tag(String tagName){
        this.tid = null;
        this.tagName = tagName;
    }

    public Integer getTid() {
        return tid;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}

package com.virtualSlime.Entity.Relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@TableName(schema = "virtual_slime",value = "r_user_follow")
public class UserFollow {
    @TableId(type = IdType.AUTO)
    private Integer uidFrom;
    private Integer uidTo;
    private Boolean isSpecialized;
    private Date createdAt;
}

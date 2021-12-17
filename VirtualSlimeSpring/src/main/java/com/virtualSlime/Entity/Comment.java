package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.virtualSlime.Enum.CommentState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@TableName(schema = "virtual_slime",value = "user_comment")
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer cid;
    private Integer uid;
    private Integer iid;
    private Date createdAt;
    private String commentContent;
    private Byte commentRating;
    private CommentState commentState;

    public Comment(int uid,int iid,Date createdAt,String content,Byte rating){
        this.uid = uid;
        this.iid = iid;
        this.createdAt = createdAt;
        this.commentContent = content;
        this.commentRating = rating;
        this.commentState = CommentState.NORMAL;
    }
}

package com.virtualSlime.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.virtualSlime.Entity.Comment;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.CommentState;
import com.virtualSlime.Mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentRepository {
    @Resource
    private CommentMapper commentMapper;

    //basic insert
    public boolean insertComment(Comment comment){
        return commentMapper.insert(comment) == 1;
    }

    //basic update
    public boolean updateComment(Comment comment){
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>().eq("cid",comment.getCid());

        return commentMapper.update(comment,wrapper) == 1;
    }

    //select methods
    public List<Comment> selectCommentsByIid(int iid){
        //this will exclude all unavailable comments
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>().eq("iid",iid);
        List<Comment> result = commentMapper.selectList(wrapper);

        result.removeIf(c -> c.getCommentState() != CommentState.NORMAL);

        return result;
    }

    public List<Comment> selectCommentsByUid(User user){
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>().eq("uid",user.getUid());

        return commentMapper.selectList(wrapper);
    }

    public Comment selectCommentByUidAndIid(User user,Item item){
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>().eq("uid",user.getUid())
                .and(w -> w.eq("iid",item.getIid()));

        return commentMapper.selectOne(wrapper);
    }

    public boolean checkCommentHasExist(User user, Item item){
        Comment comment = selectCommentByUidAndIid(user,item);

        return comment != null;
    }


}

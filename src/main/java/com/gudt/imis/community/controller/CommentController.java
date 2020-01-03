package com.gudt.imis.community.controller;

import com.gudt.imis.community.dataobject.CommentDTO;
import com.gudt.imis.community.model.Comment;
import com.gudt.imis.community.model.User;

import com.gudt.imis.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value="/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        Map<Object,Object> map=new HashMap<>();
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            map.put("message","请先进行登录后再进行评论！");
            return map;
        }
        Comment comment=new Comment();
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        if(commentService.insert(comment)!=0) {
            map.put("message", "评论成功");
        }else{
            map.put("message", "评论失败");
        }
        return map;
    }
}

package com.gudt.imis.community.service;

import com.gudt.imis.community.mapper.CommentMapper;
import com.gudt.imis.community.mapper.QuestionExtMapper;
import com.gudt.imis.community.mapper.QuestionMapper;
import com.gudt.imis.community.model.Comment;
import com.gudt.imis.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Transactional
    public int insert(Comment comment) {
        //回复问题
        if(comment.getParentId()!=0 && comment.getType()==1){
            Question question=questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question!=null){
                commentMapper.insert(comment);
                questionExtMapper.incComment(question);
                return 1;
            }
        }
        //回复评论
        if(comment.getParentId()!=0 && comment.getType()==2){
            Comment dbcomment=commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbcomment!=null){
                return commentMapper.insert(comment);
            }
        }
        return 0;
    }
}

package com.gudt.imis.community.service;

import com.gudt.imis.community.mapper.CommentMapper;
import com.gudt.imis.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }
}

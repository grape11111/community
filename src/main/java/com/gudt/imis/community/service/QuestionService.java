package com.gudt.imis.community.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.gudt.imis.community.dataobject.QuestionDTO;
import com.gudt.imis.community.mapper.QuestionMapper;
import com.gudt.imis.community.mapper.UserMapper;
import com.gudt.imis.community.model.Question;
import com.gudt.imis.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;


    public List<QuestionDTO> list() {
        List<Question> questionlist=questionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for(Question qt:questionlist){
            User user=userMapper.findById(qt.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(qt,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}

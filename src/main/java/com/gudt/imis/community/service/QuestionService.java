package com.gudt.imis.community.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.gudt.imis.community.dataobject.PaginationDTO;
import com.gudt.imis.community.dataobject.QuestionDTO;
import com.gudt.imis.community.mapper.QuestionExtMapper;
import com.gudt.imis.community.mapper.QuestionMapper;
import com.gudt.imis.community.mapper.UserMapper;
import com.gudt.imis.community.model.Question;
import com.gudt.imis.community.model.QuestionExample;
import com.gudt.imis.community.model.User;
import com.gudt.imis.community.model.UserExample;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;


    public List<QuestionDTO> list() {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria();
        List<Question> questionlist=questionMapper.selectByExample(questionExample);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for(Question qt:questionlist){
            UserExample userExample=new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(qt.getCreator());
            List<User> list=userMapper.selectByExample(userExample);
            User user=list.get(0);
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(qt,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public Integer count() {
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria();
        return questionMapper.selectByExample(questionExample).size();
    }

    public List<QuestionDTO> listByUserId(Integer id) {
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        List<Question> questionlist=questionMapper.selectByExample(questionExample);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for(Question qt:questionlist){
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(qt,questionDTO);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public Integer countByUserId(Integer id) {
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        return questionMapper.selectByExample(questionExample).size();
    }

    public QuestionDTO getById(Integer id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIdEqualTo(question.getCreator());
        User user=userMapper.selectByExample(userExample).get(0);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.insert(question);
        }else{
            QuestionExample questionExample=new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question,questionExample);
        }
    }

    public void incView(Integer id) {
        Question question=new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}

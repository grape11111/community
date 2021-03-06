package com.gudt.imis.community.controller;

import com.gudt.imis.community.dataobject.QuestionDTO;
import com.gudt.imis.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController{
    @Autowired
    private QuestionService questionService;



    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Integer id, Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        //增加阅读数
        if(questionDTO.getId()!=null){
            questionService.incView(id);
        }
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}

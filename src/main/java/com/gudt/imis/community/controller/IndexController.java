package com.gudt.imis.community.controller;

import com.github.pagehelper.PageHelper;
import com.gudt.imis.community.dataobject.GithubUser;
import com.gudt.imis.community.dataobject.PaginationDTO;
import com.gudt.imis.community.dataobject.QuestionDTO;
import com.gudt.imis.community.mapper.QuestionMapper;
import com.gudt.imis.community.mapper.UserMapper;
import com.gudt.imis.community.model.Question;
import com.gudt.imis.community.model.User;
import com.gudt.imis.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public  String index(HttpServletRequest request, Model model,
                         @RequestParam(name="page",defaultValue = "1")Integer page,
                         @RequestParam(name="size",defaultValue = "5")Integer size
    ){
        PageHelper.startPage(page,size);
        List<QuestionDTO> questionlist= questionService.list();
        PaginationDTO paginationDTO= new PaginationDTO();
        paginationDTO.setQuestions(questionlist);
        Integer totalCount=questionService.count();
        paginationDTO.setPagination(totalCount,size,page);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }

    @GetMapping("/search")
    public String findByCondition(@RequestParam(name="conditioin")String condition,
                                  HttpServletRequest request, Model model
                                  ){
        List<QuestionDTO> questionlist= questionService.listByCondition(condition);
        PaginationDTO paginationDTO= new PaginationDTO();
        paginationDTO.setQuestions(questionlist);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }

}

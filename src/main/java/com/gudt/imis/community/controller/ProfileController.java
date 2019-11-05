package com.gudt.imis.community.controller;


import com.github.pagehelper.PageHelper;
import com.gudt.imis.community.dataobject.PaginationDTO;
import com.gudt.imis.community.dataobject.QuestionDTO;
import com.gudt.imis.community.model.Question;
import com.gudt.imis.community.model.User;
import com.gudt.imis.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action")String action, HttpServletRequest request, Model model,
                          @RequestParam(name="page",defaultValue = "1")Integer page,
                          @RequestParam(name="size",defaultValue = "5")Integer size
    ){
        User user=(User)request.getSession().getAttribute("user");
        if("questions".equals(action)) {
            PageHelper.startPage(page,size);
            List<QuestionDTO> questionlist= questionService.listByUserId(user.getId());
            PaginationDTO paginationDTO= new PaginationDTO();
            paginationDTO.setQuestions(questionlist);
            Integer totalCount=questionService.countByUserId(user.getId());
            paginationDTO.setPagination(totalCount,size,page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", action);
            model.addAttribute("selectionName","我的提问");
        }else if("replies".equals(action)) {
            model.addAttribute("selection", action);
            model.addAttribute("selectionName","最新回复");
        }else if("liking".equals(action)) {
            model.addAttribute("selection", action);
            model.addAttribute("selectionName","我关注的话题");
        }
        return "profile";
    }
}

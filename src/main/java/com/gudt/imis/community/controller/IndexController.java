package com.gudt.imis.community.controller;

import com.gudt.imis.community.dataobject.GithubUser;
import com.gudt.imis.community.dataobject.QuestionDTO;
import com.gudt.imis.community.mapper.QuestionMapper;
import com.gudt.imis.community.mapper.UserMapper;
import com.gudt.imis.community.model.Question;
import com.gudt.imis.community.model.User;
import com.gudt.imis.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper usermapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public  String index(HttpServletRequest request, Model model){
        Cookie[]cookies=request.getCookies();
        if (cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName() != null) {
                    if (("Token").equals(cookie.getName())) {
                        User user = usermapper.findByToken(cookie.getValue());
                        if (user != null) {
                            request.getSession().setAttribute("user", user);
                        }
                        break;
                    }
                }
            }
        }else {
            request.getSession().setAttribute("error", "用户未登录");
        }

        List<QuestionDTO> questionlist= questionService.list();
        model.addAttribute("questions", questionlist);
        return "index";
    }
}

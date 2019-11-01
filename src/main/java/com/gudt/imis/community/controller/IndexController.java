package com.gudt.imis.community.controller;

import com.gudt.imis.community.mapper.UserMapper;
import com.gudt.imis.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper usermapper;

    @GetMapping("/")
    public  String index(HttpServletRequest request){
        Cookie[]cookies=request.getCookies();
        if (cookies!=null) {
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
        }
        request.getSession().setAttribute("error", "用户未登录");
        return "index";
    }
}

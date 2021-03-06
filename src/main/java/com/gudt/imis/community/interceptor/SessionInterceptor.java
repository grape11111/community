package com.gudt.imis.community.interceptor;

import com.gudt.imis.community.mapper.UserMapper;
import com.gudt.imis.community.model.User;
import com.gudt.imis.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper usermapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[]cookies=request.getCookies();
        if (cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName() != null) {
                    if (("Token").equals(cookie.getName())) {
                        UserExample userExample = new UserExample();
                        userExample.createCriteria()
                                .andTokenEqualTo(cookie.getValue());
                        List<User> list=usermapper.selectByExample(userExample);
                        if (list.size()!=0) {
                            request.getSession().setAttribute("user",list.get(0));
                        }
                        break;
                    }
                }
            }
        }else {
            request.getSession().setAttribute("error", "用户未登录");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}

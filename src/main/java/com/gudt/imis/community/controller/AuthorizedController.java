package com.gudt.imis.community.controller;

import com.gudt.imis.community.dataobject.AccessTokenDTO;
import com.gudt.imis.community.dataobject.GithubUser;
import com.gudt.imis.community.mapper.UserMapper;
import com.gudt.imis.community.model.User;
import com.gudt.imis.community.model.UserExample;
import com.gudt.imis.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizedController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.client.uri}")
    private String clientUri;

    @Autowired
    private UserMapper usermapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code, @RequestParam(name="state") String state, HttpServletRequest request,HttpServletResponse response) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(clientUri);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String token=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser=githubProvider.getGithubUser(token);

        if(githubUser.getName()!=null){
            User user = new User();
            String Token =UUID.randomUUID().toString();
            String accountId=String.valueOf(githubUser.getId());
            user.setToken(Token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            UserExample userExample=new UserExample();
            userExample.createCriteria()
                    .andAccountIdEqualTo(accountId);
            if((usermapper.selectByExample(userExample)).size()==0){
                usermapper.insert(user);
            }else{
                user.setId((usermapper.selectByExample(userExample)).get(0).getId());
                user.setBio((usermapper.selectByExample(userExample)).get(0).getBio());
                usermapper.updateByExample(user,userExample);
            }
            response.addCookie(new Cookie("Token",Token));
            return "redirect:/";
        }else{
            //return "redirect:http://localhost:8080/publish";
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String callback(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("Token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}

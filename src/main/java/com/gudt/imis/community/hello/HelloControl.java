package com.gudt.imis.community.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloControl {
    @GetMapping("/hello")
    public  String index(@RequestParam(name="name") String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }

}

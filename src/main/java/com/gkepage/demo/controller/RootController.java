package com.gkepage.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.gkepage.demo.db.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {


    @Autowired
    UserRepo userRepo;

    String[] mbti_list = {"ISTJ", "ISFJ", "INFJ", "INTJ", "ISTP", "ISFP", "INFP", "INTP", "ESTP", "ESFP", "ENFP", "ENTP", "ESTJ", "ESFJ", "ENFJ", "ENTJ"};



    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "Spring boot docker start!";
    }

    @RequestMapping(value="/main", method=RequestMethod.GET)
    public String main(@RequestParam(value = "toMbti", defaultValue = "ISTJ") String toMbti, 
                            @RequestParam(value="isFirst", defaultValue="false") String isFirst, Model model) throws Exception{

        System.out.println("[GET /main] toMbti: " + toMbti + ", isFirst: " + isFirst);
        //ModelAndView view = new ModelAndView("main");
        Map<String, Object> map = new HashMap<>();
        for(int i=0;i<16;i++){
            map.put(mbti_list[i],0);
        }

        Map<String, Object> fromMbti = userRepo.getAllDocuments(map, toMbti);
    
        model.addAttribute("mbti_list", mbti_list);
        model.addAttribute("toMbti", toMbti);
        model.addAttribute("map", fromMbti);
        model.addAttribute("isFirst",isFirst);
        return "main";

    }

    @ResponseBody
    @RequestMapping(value="/main", method=RequestMethod.POST)
    public String main(@RequestParam Map<String, String> params) throws Exception{
        String fromMbti = params.get("newFromMbti");
        String toMbti = params.get("newToMbti");
        String stars = params.get("newStars");
        System.out.println("[POST /main] newFromMbti: " + fromMbti + ", newToMbti: " + toMbti + ", newStars: " + stars);
        userRepo.addUser("users",fromMbti, toMbti, stars);
        return "redirect:/main?isFirst=true";
    }

    @GetMapping(path="/adduser")
    @ResponseBody
    public String addUser() throws Exception{

        userRepo.addUser("users","INTJ","ENTJ","4");
        return "success!!";
    }

    @GetMapping(path="/getuser")
    @ResponseBody
    public String getUsers() throws Exception{
        Map<String, Object> map = new HashMap<>();
        for(int i=0;i<16;i++){
            map.put(mbti_list[i],0);
        }
        System.out.println(userRepo.getAllDocuments(map, "ENTJ").toString());
        return "success!!";
    }
    
}
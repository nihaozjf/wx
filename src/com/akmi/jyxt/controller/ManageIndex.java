package com.akmi.jyxt.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManageIndex {

 	@RequestMapping(value="/manage/index/",method=RequestMethod.GET)
    public ModelAndView bxinfindex(HttpServletRequest request) throws Exception 
    {
 		 Map<String, Object> map = new HashMap<String, Object>();
		 return new ModelAndView("manageindex",map);
    }
 	
 	@RequestMapping(value="/manage/login/",method=RequestMethod.GET)
    public ModelAndView bxinflogin(HttpServletRequest request) throws Exception 
    {
 		 Map<String, Object> map = new HashMap<String, Object>();
		 return new ModelAndView("manageindex",map);
    }
}

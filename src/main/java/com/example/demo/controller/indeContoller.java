package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Visit;
import com.example.demo.service.VisitService;
@Controller
@RequestMapping("/index")
public class indeContoller {
	
	 
	   @RequestMapping("/show")
	    public String show(){
	       String msg="12312";
	       System.out.println(msg);
	        return "home";
	    }
	   @RequestMapping("/test")
		public String test(){
			
			return "test";
		}
}

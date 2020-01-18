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
@RequestMapping("/visit")
public class VisitCotroller {
	@Resource
	VisitService service;

	@ResponseBody
	@RequestMapping("/index")
	public List<Visit> index() {
		return service.queryEmpAll();
	}

	@RequestMapping("/")
	public String index(Model model) {
		String msg = "12312";
		model.addAttribute("msg", msg);
		return "home";
	}

	@RequestMapping("/test")
	public String test() {
		return "test";
	}
}

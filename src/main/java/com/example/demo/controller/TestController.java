package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    
    @Autowired
    RedisTemplate redisTemplate;
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @RequestMapping("j_spring_security_check") 
    public String check() {
		return "home";
    	
    }
    @RequestMapping("/set")
    public void set() {
        redisTemplate.opsForValue().set("test", "4321");
    }
	
	@RequestMapping("/show")
	public String show(){
	    
	    logger.info(redisTemplate.opsForValue().get("test").toString());
		return "Hello World";		
	}
	@GetMapping("/setSessionValue")
	@ResponseBody
	public String setredisResult(HttpServletRequest request){
	    request.getSession().setAttribute(request.getSession().getId(), "---测试数据---"+request.getRequestURL());
	 
	    System.out.println(request.getSession().getId());
	    return "set成功，已经存入session域且redis里面也会有值";
	}
	@GetMapping("/getSessionValue")
	@ResponseBody
	public String redisResult(HttpServletRequest request) {
	    System.out.println(request.getSession().getId());
	    String value = String.valueOf(request.getSession().getAttribute(request.getSession().getId()));
	    return "取值成功         :"+value;
	}
	 
}
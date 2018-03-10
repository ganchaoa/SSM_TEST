package com.gan.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Hello")
public class HelloWorldController {
	 @RequestMapping("/SayHello")   
	    public String SayHello(Model model) {        
	        model.addAttribute("message", "Hello Spring MVC!");
	        System.out.println(model.containsAttribute("message")); //是否成功赋值
	return "hello";
	    }   
}

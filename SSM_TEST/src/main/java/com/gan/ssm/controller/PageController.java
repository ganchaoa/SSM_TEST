package com.gan.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.gan.ssm.websocket.WebSocketHandler;

@Controller 
public class PageController {

	@Bean    //这个注解会从Spring容器拿出Bean  
	private WebSocketHandler infoHandler() {
		return new WebSocketHandler();
	}
	
	@RequestMapping(value="login2",method=RequestMethod.GET)
	public void login2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("username");
		System.out.println(userName + "登录");
		session.setAttribute("SESSION_USERNAME", userName);  
		request.getRequestDispatcher("/WEB-INF/view/websocket/websocket.jsp").forward(request, response);  
	}
	
	@RequestMapping("/send")  
	@ResponseBody  
	public String send(HttpServletRequest request){
		  String username = request.getParameter("username");  
	        infoHandler().sendMessageToUser(username, new TextMessage("你好，欢迎测试！！！！"));  
	        return null;  
	}

	
	
}

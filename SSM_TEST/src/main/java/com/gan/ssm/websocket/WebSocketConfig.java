package com.gan.ssm.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements
		WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 注册webSocket
		String webSocket = "/websocket/socketServer.do";                                     //设置websocket的地址  	
		registry.addHandler(webSocketHandler(), webSocket)                                //注册Handler  
		.addInterceptors(new WebSocketHandshakeInterceptor());                            //注册Interceptor  
		
		  String sockjs_url = "/sockjs/socketServer.do";                                    //设置sockjs的地址  
	        registry.addHandler(webSocketHandler(), sockjs_url).                            //注册Handler  
	                addInterceptors(new WebSocketHandshakeInterceptor()).                   //注册Interceptor  
	                withSockJS();                                                           //支持sockjs协议  

	}

	
	@Bean  
	public TextWebSocketHandler webSocketHandler() {
		 return new com.gan.ssm.websocket.WebSocketHandler();  
	}

	
}

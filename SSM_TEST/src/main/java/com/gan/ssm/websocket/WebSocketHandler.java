package com.gan.ssm.websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);  
	
	 //已建立连接的用户  
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();  
	
    
    
    /* (non-Javadoc)
     * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#handleTextMessage(org.springframework.web.socket.WebSocketSession, 
     * org.springframework.web.socket.TextMessage)
     * 
     * 
     * 处理前端发送的文本信息
     * js调用websocket.send时候，会调用该方法
     */
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
    	String userName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
    	//获取提交过的信息详情
    	LOGGER.debug("收到用户"+ "  的消息" + message.toString());
    	//回复一条信息
    	session.sendMessage(new TextMessage("reply msg:" + message.getPayload()));
    }

    
    /* (non-Javadoc)
     * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#afterConnectionEstablished(org.springframework.web.socket.WebSocketSession)
     * 但新连接建立的时候，被调用
     * 连接成功时,会触发页面上的onOpen()
     * 
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
    	users.add(session);
    	String userName = (String)session.getAttributes().get("WEBSOCKET_USERNAME");
    	LOGGER.info("用户 "+ userName + " 建立连接");
    	session.sendMessage(new TextMessage(userName +"连接了"));
    	session.sendMessage(new TextMessage("欢迎！"));
    	
    }
    
    
    /**
     * @param session
     * @param status
     * @throws Exception
     * 
     * 当连接关闭时被调用
     */
    public void afterConnectionClose(WebSocketSession session, CloseStatus status) throws Exception{
    	String userName = (String)session.getAttributes().get("WEBSOCKET_USERNAME");
    	if(session.isOpen()){
    		session.close();
    	}
    	LOGGER.debug("用户 " +userName + " websocket 连接关闭");
    	users.remove(session);
    }
    
    /**
     * @param session
     * @param status
     * @throws Exception
     * 
     * 当传输错误的时候调用
     */
    public void handleTransportError(WebSocketSession session,CloseStatus status) throws Exception{
    	String userName = (String)session.getAttributes().get("WEBSOCKET_USERNAME");
    	if(session.isOpen()){
    		session.close();
    	}
    	LOGGER.debug("用户 " +userName + " websocket 连接关闭");
    	users.remove(session);
    }
    
    /**
     * @param message
     * 给所有用户发送消息
     */
    public void sendMessageToUsers(TextMessage message){
    	for (WebSocketSession user : users) {
			try{
				if(user.isOpen()){
					user.sendMessage(message);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
    }
    
    public void sendMessageToUser(String userName, TextMessage message){
    	 for (WebSocketSession user : users) {  
             if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {  
                 try {  
                     if (user.isOpen()) {  
                         user.sendMessage(message);  
                     }  
                 } catch (IOException e) {  
                     e.printStackTrace();  
                 }  
                 break;  
             }  
         }  
    }
}
       
package com.mzc.controller;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 类似Servlet的注解mapping。无需在web.xml中配置。
 * configurator = SpringConfigurator.class是为了使该类可以通过Spring注入。
 */
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。
@ServerEndpoint(value = "/websocket/{userId}",configurator = SpringConfigurator.class)
public class MyWebSocket {
    public MyWebSocket() {
    }
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet=new CopyOnWriteArraySet<>();
    //与客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        Map<String, String> map = session.getPathParameters();
        String userId = map.get("userId");
        /*群发消息*/
        for(MyWebSocket item: webSocketSet){
            if(item.session!=session){
                try {
                    item.sendMessage("<div class=border style=text-align:center>" +
                            userId+
                            "加入了聊天室</div>");
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }

        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        Map<String, String> map = session.getPathParameters();
        String userId = map.get("userId");
        for(MyWebSocket item: webSocketSet){
                try {
                    item.sendMessage("<div class=border style=text-align:center>" +
                            userId+
                            "离开了聊天室</div>");
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
        }
    }
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
     @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //userId
         Map<String, String> map = session.getPathParameters();
         String userId = map.get("userId");
         //时间
         DateFormat df =new SimpleDateFormat("HH:mm:ss");
         String time = df.format(new Date());
        //群发消息
        for(MyWebSocket item: webSocketSet){
            if(item.session!=session){
                try {
                    item.sendMessage("<div class=border style=text-align:left>" +
                            userId+" "+time+":<br>"+
                            message+"<br>"+
                            "</div>");
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }else{
                try {
                    item.sendMessage("<div class=border style=text-align:right>" +
                            userId+" "+time+":<br>"+
                            message+"<br>"+
                            "</div>");
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }

        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws  IOException {
        //保存数据到数据库
//        Content content = new Content() ;
//        content.setContent(message);
//        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd") ;
//        content.setCreateDate(sm.format(new Date()));
//        contentService.insertSelective(content) ;
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }






    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}

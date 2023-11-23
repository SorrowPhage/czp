package com.sorrowphage.czp.socket;

import com.sorrowphage.czp.config.WebSocketConfig;
import com.sorrowphage.czp.encoder.ServerEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: SorrowPhage
 * @date: 2023/11/8
 * 聊天websocket
 */
@Slf4j
@ServerEndpoint(value = "/msg/chat/{userId}", encoders = ServerEncoder.class, configurator = WebSocketConfig.class)
@Component
public class MsgChatWebSocketServer {

    /**
     * 与某个客户端的连接对话，需要通过它来给客户端发送消息
     */
    private Session session;

    /**
     * 标识当前连接客户端的账号
     */
    private String userId;

    /**
     * 用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    private static final ConcurrentHashMap<String, MsgChatWebSocketServer> webSocketSet = new ConcurrentHashMap<>();


    /**
     * 建立链接
     *
     * @param session session
     * @param userId  链接用户
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        this.session = session;
        this.userId = userId;
        System.out.println(session);
        webSocketSet.put(userId, this);
        log.info(userId + "链接");
        log.info("当前人数:" + webSocketSet.size());
        this.appointSending(userId, "hello");
    }

    /**
     * 关闭链接
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this.userId);
    }

    /**
     * 收到客户端消息
     *
     * @param messageStr msg
     */
    @OnMessage
    public void onMessage(String messageStr) {
        //解析消息
        //处理消息
        // this.appointSending(userId, "hello");
    }

    /**
     * 指定发生
     *
     * @param userId  接收对象
     * @param message 发送消息
     */
    public void appointSending(String userId, String message) {
        try {
            webSocketSet.get(userId).session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //判断用户是否在线
    public boolean isOnline(String userId) {
        return !Objects.isNull(webSocketSet.get(userId));
    }


    /**
     * 发生错误时调用
     *
     * @param session session
     * @param error   error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


}

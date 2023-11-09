package com.sorrowphage.czp.socket;

import com.sorrowphage.czp.entity.Audit;
import com.sorrowphage.czp.service.AuditService;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: SorrowPhage
 * @date: 2023/11/8
 */
@ServerEndpoint("/audit")
@Component
public class AuditWebSocket {

    /**
     * 与某个客户端的连接对话，需要通过它来给客户端发送消息
     */
    private Session session;


    /**
     * 标识当前连接客户端的用户名
     */
    private String name;

    /**
     * 用于存所有的连接服务的客户端，这个对象存储是安全的
     * 注意这里的kv,设计的很巧妙，v刚好是本类 WebSocket (用来存放每个客户端对应的MyWebSocket对象)
     */
    private static final ConcurrentHashMap<String, AuditWebSocket> webSocketSet = new ConcurrentHashMap<>();

}

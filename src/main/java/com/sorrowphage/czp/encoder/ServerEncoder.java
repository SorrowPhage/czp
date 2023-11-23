package com.sorrowphage.czp.encoder;

import com.alibaba.fastjson.JSON;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author: SorrowPhage WebSocket编码器，服务端发送数据到客户端时，可以发送Object对象，即对象转换为JSON数据
 * @date: 2023/11/23
 */
public class ServerEncoder implements Encoder.Text<Object>{
    @Override
    public String encode(Object o) throws EncodeException {
        return JSON.toJSONString(o);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}

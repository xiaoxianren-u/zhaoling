package com.edu.Websocket;

/**
 * @Class: WebSocketMessageService
 * @Description: 使用webscoket连接向用户发送信息
 * @author JFPZ
 * @date 2017年5月15日 上午20:17:01
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("webSocketMessageService")
public class WsMessageService {
    private final Logger logger = LoggerFactory.getLogger(WsMessageService.class);
    //声明websocket连接类
    private final WebsocketDemo websocketDemo = new WebsocketDemo();

    /**
     * @Title: sendToAllTerminal
     * @Description: 调用websocket类给用户下的所有终端发送消息
     * @param @param userId 用户id
     * @param @param message 消息
     * @param @return 发送成功返回true，否则返回false
     */
    public Boolean sendToAllTerminal(Long userId, String message) {
        logger.debug("向用户{}的消息：{}", userId, message);
        return websocketDemo.sendMessageToUser(userId, message);
    }
}


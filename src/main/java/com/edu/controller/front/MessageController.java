package com.edu.controller.front;


import com.edu.Websocket.WsMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private WsMessageService wsMessageService;

    /**
     * 请求入口
     *
     * @param userId
     * @param message
     * @return
     */
    @RequestMapping(value = "/TestWS", method = RequestMethod.GET)
    public String testWs(@RequestParam(value = "userId") Long userId,
                         @RequestParam(value = "message") String message) {


        System.out.println("message = " + message);
        System.out.println("userId = " + userId);
        logger.warn("收到发送请求，向用户{}的消息：{}", userId, message);
        if (wsMessageService.sendToAllTerminal(userId, message)) {

            return "success";
        } else {

            return "error";
        }
    }
}


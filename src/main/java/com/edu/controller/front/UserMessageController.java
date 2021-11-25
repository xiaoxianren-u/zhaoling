package com.edu.controller.front;


import com.alibaba.fastjson.JSON;
import com.edu.pojo.UserMessage;
import com.edu.service.UserMessageService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author yz
 * @data: 2021/11/23 13:18 星期二
 * @file : UserMessageController.java
 */


/**
 * websocket类
 * socket链接地址
 */


@ServerEndpoint("/websocket1/{username}")
@Controller
@Component
public class UserMessageController {

    /**
     * 设置一次性存储数据的list的长度为固定值，每当list的长度达到固定值时，向数据库存储一次
     */
    private static final Integer LIST_SIZE = 3;
    /**
     * 新建list集合存储数据
     */
    private static final ArrayList<UserMessage> MessageList = new ArrayList<>();
    /**
     * map(username,websocket)作为对象添加到集合中
     */
    private static final Map<String, UserMessageController> clients = new ConcurrentHashMap<String, UserMessageController>();
    /**
     * 设置在线人数为静态变量
     */
    public static int onlineNumber = 0;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMessageService userMessageService;
    /**
     * session会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String username;

    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }

//    @Autowired
//    public void setOgLocationService(UserMessageService userMessageService) {
//        UserMessageController.userMessageService = userMessageService;
//    }

    /**
     * 进入聊天室 --> 项目中读取用户信息获取用户名
     */
    @RequestMapping("/websocket1")
    public String webSocket(@NotNull Model model) {

        //根据时间随机定义名称
        String name = "游客：";
        String datename = new SimpleDateFormat("msss").format(new Date());
        name = name + datename;

        //websock链接地址+游客名-->  项目中请定义在配置文件 -->或直接读取服务器，ip 端口
        String path = "ws://127.0.0.1:1012/websocket1/";
        model.addAttribute("path", path);
        model.addAttribute("username", name);
        return "front/socket";
    }

    /**
     * 监听连接（有用户连接，立马到来执行这个方法）
     * session 发生变化
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, @NotNull Session session) {

        //每打开一个新的窗口，在线人数onlineNumber++
        onlineNumber++;
        //把新用户名赋给变量
        this.username = username;
        //把新用户的 session 信息赋给变量
        this.session = session;
        //输出 websocket 信息
        logger.info("现在来连接的客户id：" + session.getId() + "用户名：" + username);
        logger.info("有新连接加入！ 当前在线人数" + onlineNumber);
        try {
            //把自己的信息加入到map当中去，this=当前类（把当前类作为对象保存起来）
            clients.put(username, this);
            //获得所有的用户lists
            Set<String> lists = clients.keySet();

            // 发送全体信息（新用户上线信息）

            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            Map<String, Object> map1 = new HashMap(100);
            //  把所有用户列表
            map1.put("onlineUsers", lists);
            //  返回上线状态
            map1.put("messageType", 1);
            //  返回用户名
            map1.put("username", username);
            //  返回在线人数
            map1.put("number", onlineNumber);

            //  发送全体信息（用户上线信息）
            sendMessageAll(JSON.toJSONString(map1), username);

            // 给自己发一条消息：告诉自己现在都有谁在线
            Map<String, Object> map2 = new HashMap(100);
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            map2.put("messageType", 3);
            //把所有用户放入map2
            map2.put("onlineUsers", lists);
            //返回在线人数
            map2.put("number", onlineNumber);

            // 消息发送指定人（所有的在线用户信息）
            sendMessageTo(JSON.toJSONString(map2), username);
        } catch (IOException e) {
            logger.info(username + "上线的时候通知所有人发生了错误");
        }
    }

    /**
     * 监听连接断开（有用户退出，会立马到来执行这个方法）
     */
    @OnClose
    public void onClose() {
        //每关闭一个新的窗口，在线人数onlineNumber--
        onlineNumber--;
        //从所有在线用户的map中去除下线用户
        clients.remove(username);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap(100);
            map1.put("messageType", 2);
            //所有在线用户
            map1.put("onlineUsers", clients.keySet());
            //下线用户的用户名
            map1.put("username", username);
            //返回在线人数
            map1.put("number", onlineNumber);
            //发送信息，所有人，通知谁下线了
            sendMessageAll(JSON.toJSONString(map1), username);
            //关闭连接前，判断list集合是否有数据，如果有，批量保存到数据库
            if (MessageList.size() < LIST_SIZE) {
                userMessageService.saveBatch(MessageList);
            }
        } catch (IOException e) {
            logger.info(username + "下线的时候通知所有人发生了错误");
        }
        logger.info("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    @OnError
    public void onError(Session session, @NotNull Throwable error) {
        logger.info("服务端发生了错误" + error.getMessage());
        //error.printStackTrace();
    }

    /**
     * 监听消息（收到客户端的消息立即执行）
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
            //用户发送的信息
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(message);
            //发送的内容
            String textMessage = jsonObject.getString("message");
            //发送人
            String fromusername = jsonObject.getString("username");
            //接收人  to=all 发送消息给所有人 || to= !all   to == 用户名
            String tousername = jsonObject.getString("to");

            //新建message对象
            UserMessage message1 = new UserMessage();

            //设置发送者的username
            message1.setUsername(fromusername);
            //设置发送的信息
            message1.setMessage(textMessage);
            //设置发送时间
            message1.setCreatetime(new Date());
            //判断接收者
            if (tousername.equals("All")) {
                message1.setTousername("All");
            } else {
                message1.setTousername(tousername);
            }
            //批量保存信息
            //将每条记录添加到list集合中
            MessageList.add(message1);
            //判断list集合长度
            if (MessageList.size() == LIST_SIZE) {
                userMessageService.saveBatch(MessageList);
                //清空集合
                MessageList.clear();
            }


            //发送消息  -- messageType 1代表上线 2代表下线 3代表在线名单  4代表消息
            Map<String, Object> map1 = new HashMap(100);
            map1.put("messageType", 4);
            map1.put("textMessage", textMessage);
            map1.put("fromusername", fromusername);
            if (tousername.equals("All")) {
                //消息发送所有人（同步）
                map1.put("tousername", "所有人");
                sendMessageAll(JSON.toJSONString(map1), fromusername);
            } else {
                //消息发送指定人（同步）
                map1.put("tousername", tousername);
                sendMessageTo(JSON.toJSONString(map1), tousername);
            }
        } catch (Exception e) {
            logger.info("发生了错误了");
        }
    }

    /**
     * 消息发送指定人
     */
    public void sendMessageTo(String message, String toUserName) throws IOException {
        //遍历所有用户
        for (UserMessageController item : clients.values()) {
            if (item.username.equals(toUserName)) {
                //消息发送指定人（同步）
                item.session.getBasicRemote().sendText(message);
                break;
            }
        }
    }

    /**
     * 消息发送所有人
     */
    public void sendMessageAll(String message, String fromUserName) throws IOException {
        for (UserMessageController item : clients.values()) {
            //消息发送所有人（同步）getAsyncRemote
            item.session.getBasicRemote().sendText(message);
        }
    }
}

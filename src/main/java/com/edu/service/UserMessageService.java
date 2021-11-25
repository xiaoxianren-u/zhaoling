package com.edu.service;

import com.edu.pojo.UserMessage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author yz
 * @data: 2021/11/23 13:24 星期二
 * @file : UserMessageService.java
 */

@Repository
public interface UserMessageService {

    void saveBatch(ArrayList<UserMessage> messageList);
}

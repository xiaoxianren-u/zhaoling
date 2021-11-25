package com.edu.service.impl;

import com.edu.pojo.UserMessage;
import com.edu.service.UserMessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author yz
 * @data: 2021/11/23 13:26 星期二
 * @file : UserMessageServiceImpl.java
 */

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Override
    public void saveBatch(ArrayList<UserMessage> messageList) {

    }
}

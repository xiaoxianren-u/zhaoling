package com.edu.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yz
 * @data: 2021/11/17 23:54 星期三
 * @file : Start.java
 */
@Component
public class Start {

    @PostConstruct
    public void start() {


        System.out.println("           (♥◠‿◠)ﾉﾞ  招领启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "        /---------------\\         |..|                      \n" +
                "        | - - - - -\\    \\         |..|                      \n" +
                "                    \\  /          |..|                      \n" +
                "                   /  /           |..|                      \n" +
                "                 /  /             |..|                      \n" +
                "               /  /               |..|                      \n" +
                "             /  /                 |..|                      \n" +
                "          /  /                    |..|               __    \n" +
                "        |  / - - - - - - \\        |..|_  _  _  _  _ |   \\  \n" +
                "        | ''-' `'-' `-..-'|       |.. _ - _ - _ - _ - _ |\\                 ");

    }
}

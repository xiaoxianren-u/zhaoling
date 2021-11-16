package com.edu.controller.back;

import com.edu.intercept.UserLoginToken;
import com.edu.pojo.Post;
import com.edu.service.SysPostService;
import com.edu.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author yz
 * @data: 2021/11/15 21:27 星期一
 * @file : SysPostController.java
 */

@RestController
@RequestMapping(value = "/sys/post")
public class SysPostController extends AjaxUtils {


    @Autowired
    private SysPostService sysPostService;

    /**
     * 帖子列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @UserLoginToken(state = 1)
    public AjaxUtils postAll(@RequestParam("user_name") String userName,    //用户名
                             @RequestParam("post_title") String postTitle,  //标题
                             @RequestParam("post_substance") String postSubstance,  // 内容
                             @RequestParam("post_found_place") String postFoundPlace, // 地点
                             @RequestParam("lab_name") String labName,  //标签
                             @RequestParam("post_time") String post_time,  //发帖时间
                             @RequestParam("post_found_time") String postFound_time,  //捡到或遗失时间
                             @RequestParam("post_status1") Integer postStatus1,  //捡到0或遗失1
                             @RequestParam("page") Integer page,
                             @RequestParam("limit") Integer limit) {
        Post post = new Post(userName, postTitle, postStatus1, null, postSubstance, null, null, postFoundPlace);

        //  时间装换
        if (!"".equals(post_time) && post_time != null) {
            try {
                post.setPost_time(new SimpleDateFormat("yyyy-MM-dd").parse(post_time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!"".equals(postFound_time) && postFound_time != null) {
            try {
                post.setPost_found_time(new SimpleDateFormat("yyyy-MM-dd").parse(postFound_time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // 中文乱码
        if (!"".equals(postTitle) && postTitle != null) {
            post.setPost_title(new String(postTitle.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        if (!"".equals(labName) && labName != null) {
            post.setLab_name(new String(labName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        if (!"".equals(postFoundPlace) && postFoundPlace != null) {
            post.setPost_found_place(new String(postFoundPlace.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        if (!"".equals(postSubstance) && postSubstance != null) {
            post.setPost_substance(new String(postSubstance.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        page = page > 1 ? limit * (page - 1) : 0;
        return sysPostService.selectAll(page, limit, post);
    }
}

package com.edu.controller.front;

import com.edu.pojo.Post;
import com.edu.service.ForPostService;
import com.edu.util.AjaxUtils;
import com.edu.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yz
 * @data: 2021/11/6 14:12 星期六
 * @file : ForPsotController.java
 */

@Controller
@ResponseBody
@RequestMapping(value = "/post")
public class ForPostController extends AjaxUtils {


    @Autowired
    private ForPostService forPostService;


    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public AjaxUtils insetImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        String imagePath = UploadUtils.upload(file, request, "forImage");
        System.out.println("image = " + imagePath);
        if (imagePath != null) {
            return new AjaxUtils(1, true, "上传成功", imagePath);
        } else {
            return new AjaxUtils(0, false, "上传失败");
        }

    }

    /**
     * 新增帖子
     *
     * @param post
     * @return
     */
    @RequestMapping(value = "/textPost", method = RequestMethod.POST)
    public AjaxUtils insertPost(@RequestBody Post post, HttpServletRequest request) {

        return forPostService.insertPost(post, request);
    }


    /**
     * 个人帖子表
     */

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxUtils selectList(@RequestParam("status") Integer status, HttpServletRequest request) {
        return forPostService.selectList(status, request);
    }


}

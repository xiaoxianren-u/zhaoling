package com.edu.controller.front;

import com.edu.config.UserConfig;
import com.edu.intercept.UserLoginToken;
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


    /**
     * 图片上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public AjaxUtils insetImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        String imagePath = UploadUtils.upload(file, request, "forImage");
        System.out.println("imagePath = " + imagePath);
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
     * 发布帖子表
     */

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxUtils selectList(@RequestParam("status") Integer status, HttpServletRequest request) {
        return forPostService.selectList(status, request);
    }


    /**
     * 我的物品表
     */

    @RequestMapping(value = "/listApp", method = RequestMethod.GET)
    public AjaxUtils selectListApp(@RequestParam("status") Integer status, HttpServletRequest request) {
        return forPostService.selectListApp(status, request);
    }


    /**
     * 首页帖子数量
     *
     * @param status
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public AjaxUtils selectCountIndex(@RequestParam("status") Integer status,
                                      @RequestParam("lab_name") String labName,
                                      @RequestParam("text") String text,
                                      @RequestParam("time") Integer time,
                                      @RequestParam("post_status1") Integer postStatus1) {


        return forPostService.selectCountIndex(status, labName, text, time, postStatus1);
    }


    /**
     * 首页内容列表
     *
     * @param status
     * @return
     */
    @RequestMapping(value = "/plist", method = RequestMethod.GET)
    public AjaxUtils selectListIndex(@RequestParam("status") Integer status,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("limit") Integer limit,
                                     @RequestParam("lab_name") String labName,
                                     @RequestParam("text") String text,
                                     @RequestParam("time") Integer time,
                                     @RequestParam("post_status1") Integer postStatus1) {
        page = page > 1 ? limit * (page - 1) : 0;
        return forPostService.selectListIndex(status, page, limit, labName, text, time, postStatus1);
    }


    /**
     * 申请归还
     *
     * @param post_id
     * @param request
     * @return
     */

    @UserLoginToken(state = 0)
    @RequestMapping(value = "/getPost")
    public AjaxUtils getPost(@RequestParam("post_id") Integer post_id, HttpServletRequest request) {
        return forPostService.getPost(UserConfig.tokenUserName(request), post_id);
    }

    /**
     * 拒绝归还
     *
     * @param post_id
     * @return
     */
    @UserLoginToken(state = 0)
    @RequestMapping(value = "/getExit")
    public AjaxUtils getExit(@RequestParam("post_id") Integer post_id) {
        return forPostService.getExit(post_id);
    }


    /**
     * 归还物品
     *
     * @param time
     * @param post_image
     * @return
     */
    @UserLoginToken(state = 0)
    @RequestMapping(value = "/upWu")
    public AjaxUtils setWu(@RequestParam("time") String time,
                           @RequestParam("post_image") String post_image,
                           @RequestParam("post_id") Integer post_id) {
        return forPostService.setWu(time, post_image, post_id);
    }

}

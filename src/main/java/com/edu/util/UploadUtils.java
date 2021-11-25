package com.edu.util;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

/**
 * @author yz
 * @data: 2021/10/23 11:39 星期六
 * @file : UploadUtils.java
 */
@Component
public class UploadUtils {

//    private static  String SERVER_PATH = "/static/upload/";
    //访问图片的地址
    /**
     * 上传保存的地址
     */

    public static String BASE_PATH = null;

    @Nullable
    public static String upload(MultipartFile file, HttpServletRequest request, String url) {
        String SERVER_PATH = "/static/upload/";


        request.getSession().setAttribute("file", file);
        MultipartFile file1 = (MultipartFile) request.getSession().getAttribute("file");

        BASE_PATH = request.getSession().getServletContext().getRealPath("/");

        SERVER_PATH = SERVER_PATH + url + "/";

//        System.out.println("url = " + url);
//        System.out.println("request = " + request);
//        System.out.println("file = " + file);
        String[] output = BASE_PATH.split("target");
        String BASE_PATH_TO = BASE_PATH + "\\static\\upload\\" + url + "\\";
        BASE_PATH = output[0] + "src\\main\\webapp\\static\\upload\\" + url + "\\";
        //获取上传文件的名称
        String fileName = file.getOriginalFilename();
//       用于判断是否只由数字跟字母组成
        String pattern = "[\\w]+[.]+[\\w]+";
        if (!fileName.matches(pattern)) {
            return null;
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String newFileName = uuid + "-" + fileName;
        // 创建一个文件实例对象
        File image = new File(BASE_PATH, newFileName);
        File imageFile = new File(BASE_PATH_TO, newFileName);

        //对文件进行上传操作 file 只能使用一次用完后就关闭了
        try {
            file.transferTo(image);
        } catch (IOException e) {
            return null;
        }
        //对图片进行复制
        try {
            copyImage(image, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("SERVER_PATH + newFileName = " + SERVER_PATH + newFileName);
        //返回请求路径
        return SERVER_PATH + newFileName;
    }

    /**
     * 图片复制
     *
     * @param image
     * @param imageFile
     * @throws IOException
     */
    public static void copyImage(File image, File imageFile) throws IOException {
        FileInputStream in = new FileInputStream(image);
        FileOutputStream out = new FileOutputStream(imageFile);
        BufferedInputStream bufferedIn = new BufferedInputStream(in);
        BufferedOutputStream bufferedOut = new BufferedOutputStream(out);
        byte[] by = new byte[1];
        while (bufferedIn.read(by) != -1) {
            bufferedOut.write(by);
        }
        //将缓冲区中的数据全部写出
        bufferedOut.flush();
        bufferedIn.close();
        bufferedOut.close();
    }


}

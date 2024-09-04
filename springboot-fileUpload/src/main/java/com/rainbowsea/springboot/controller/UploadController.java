package com.rainbowsea.springboot.controller;


import com.rainbowsea.springboot.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller  // 控制器
public class UploadController {

    // 处理转发到用户注册~可以完成文件上传页面
    @GetMapping("/upload.html")
    public String uploadPage() {

        return "upload";  // 视图解析，pom_>jar,转发到templates/upload.html
        // 注意如果配置了拦截器，资源的上传 upload.html页面也是要放行的
    }


    // 处理用户的注册请求-包括处理文件上传

    // 静态方式，先手动在指定的windows当中创建目录，在放入文件
    /*@PostMapping("/upload")
    @ResponseBody
    public String upload(
            // 自动封装
            String name,
            String email,
            Integer age,
            String job,
            @RequestParam("header") MultipartFile header,
            @RequestParam("photos") MultipartFile[] photos
    ) throws IOException {
        log.info("上传的信息 name={},email={} age = {} job={} ",name,email,age,job);
        log.info("header={}",header);
        log.info("photos={}",photos);


        // 如果信息都成功得到，我们就将文件保存到指定的目录比如：E:\temp_upload
        // 1. 我们需要先将文件保存到指定的目录，比如：E:\temp_upload
        // 2. 后面我们在演示把文件保存到动态创建的目录当中
        // 首先处理头像的文件，因为只有一张图片
        if(!header.isEmpty()) {  // 处理头像
            String originalFilename = header.getOriginalFilename();
            System.out.println(originalFilename);
            header.transferTo(new File("E:\\temp_upload\\" +originalFilename) );
        }

        // 处理宠物的图片
        if(photos.length > 0) {
            for (MultipartFile photo :photos) {
                String originalFilename = photo.getOriginalFilename();
                System.out.println(originalFilename);
                photo.transferTo(new File("E:\\temp_upload\\" + originalFilename));
            }
        }



        return "注册用户成功/文件上传成功";
    }*/


    // 动态创建方式：以项目作为根路径创建目录，存放文件
    // springboot-fileUpload/src/main/resources/static/images
    @PostMapping("/upload")
    @ResponseBody
    public String upload(
            // 自动封装
            String name,
            String email,
            Integer age,
            String job,
            @RequestParam("header") MultipartFile header,
            @RequestParam("photos") MultipartFile[] photos
    ) throws IOException {
        log.info("上传的信息 name={},email={} age = {} job={} ", name, email, age, job);
        log.info("header={}", header);
        log.info("photos={}", photos);


        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println("path:" + path);  // 获取到的是这个项目的根路径(类路径)全的。

        // 定义好，我们要在项目中的什么位置存放传过来的文件
        //File file = new File(path + "static/images");

        File file = new File(WebUtils.getUploadFileDirectory());
        // 动态创建指定目录
        if (!file.exists()) { // 如果目录不存在，我们就创建
            file.mkdirs();
        }

        // 首先处理头像的文件，因为只有一张图片
        if (!header.isEmpty()) {  // 处理头像
            String originalFilename = header.getOriginalFilename();  // 获取的文件的名字
            System.out.println(file.getAbsolutePath());  // 获取到文件的绝对路径
            String fileName = UUID.randomUUID().toString() + "_"+ System.currentTimeMillis()+"_"+originalFilename;
            System.out.println(fileName);
            header.transferTo(new File(file.getAbsolutePath() + "/" + fileName));
        }

        // 处理宠物的图片
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                String originalFilename = photo.getOriginalFilename();
                System.out.println(file.getAbsolutePath());  // 获取到文件的绝对路径
                String fileName = UUID.randomUUID().toString() + "_"+ System.currentTimeMillis()+"_"+originalFilename;
                // 保存到动态创建的目录
                photo.transferTo(new File(file.getAbsolutePath() + "/" + fileName ));
            }
        }


        return "注册用户成功/文件上传成功";
    }
}

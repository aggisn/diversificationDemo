package com.example.demo.controller;

import com.example.demo.util.DownloadFileUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZLJ
 * @description
 * @date 2022/8/12
 */
@RestController
@RequestMapping("/test")
public class DownloadFileController {

    @Resource
    private HttpServletResponse response;

    @Resource
    private HttpServletRequest request;

    /**
     * 本地文件单个下载
     */
    @GetMapping(value = "/down/file")
    public void downloadFileStream() {
        // 文件本地位置
        String filePath = "E:\\背景图片.jpg";
        // 文件名称
        String fileName = "测试文件.jpg";
        File file = new File(filePath);
        DownloadFileUtil.downloadFile(file, request, response, fileName);
        // 浏览器访问：http://x.x.x.x/test/down/file
    }

    /**
     * 网络文件单个下载
     */
    @GetMapping(value = "/down/file/http")
    public void downloadFileHttpStream() {
        // 文件网络地址
        String urlStr = "https://code.jquery.com/jquery-3.6.0.js";
        // 文件名称
        String fileName = "测试文件.js";
        DownloadFileUtil.downloadHttpFile(urlStr, request, response, fileName);
        // 浏览器访问：http://x.x.x.x/test/down/file/http
    }


    /**
     * 本地批量下载  zip格式
     */
    @GetMapping(value = "/down/zip")
    public void downloadZipStream() {
        List<Map<String, String>> mapList = new ArrayList<>();
        String basePath = "F:\\测试文本\\";
        // 模拟下载本地的5个文件，分别为测试文件1~5
        for (int i = 1; i <= 5; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("path", basePath + i + ".txt");
            map.put("name", "测试文件_" + i + ".txt");
            mapList.add(map);
        }
        DownloadFileUtil.zipDirFileToFile(mapList, request, response);

        // 浏览器访问：http://127.0.0.1:9011/test/down/zip
    }


    /**
     * 网络文件批量下载  zip格式
     */
    @GetMapping(value = "/down/zip/http")
    public void downloadZipHttpByDataStream() {
        List<Map<String, String>> mapList = new ArrayList<>();
        String urlStr = "https://code.jquery.com/jquery-3.6.0.js";
        // 模拟下载本地的5个文件，分别为测试文件1~5
        for (int i = 1; i <= 5; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("path", urlStr);
            map.put("name", "测试文件_" + i + ".js");
            mapList.add(map);
    }
        DownloadFileUtil.zipUrlToFile(mapList, request, response);

        // 浏览器访问：http://127.0.0.1:9011/test/down/zip/http
    }
}

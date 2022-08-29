package com.example.demo.controller;


import com.example.demo.service.MinIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZLJ
 * @description
 * @date 2022/5/10
 */
public class MinIOController {

    @Autowired
    private MinIOService minIOService;
    /**
     * 根据文件路径得到MinIO预览文件绝对地址
     *
     * @param fileName    文件路径
     * @return
     */
    @GetMapping("/getPreviewFileUrl")
    public String getPreviewFileUrl(@RequestParam String fileName) {
        return minIOService.getPreviewFileUrl(minIOService.getMinioClient(),fileName);
    }
}

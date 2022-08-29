package com.example.demo.demoCount.work;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZLJ
 * @description
 * @date 2022/6/27
 */
@RequestMapping("/auth/exportWord")
@RestController
public class ExportWordController {

    /**
     * 用户信息导出word
     * @throws IOException
     */
    @RequestMapping("/exportUserWord")
    public void exportUserWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" +new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".docx");
        Map<String, Object> params = new HashMap<>();
        // 渲染文本
        params.put("name", "张三");
        params.put("position", "开发工程师");
        params.put("entry_time", "2020-07-30");
        params.put("province", "江苏省");
        params.put("city", "南京市");
        // 渲染图片
    //    params.put("picture", new PictureRenderData(120, 120, "D:\\cssTest\\square.png"));
        // TODO 渲染其他类型的数据请参考官方文档
        String templatePath = "F:\\登记表模板.docx";

        WordUtil.downloadWord(response.getOutputStream(), templatePath, params);
    }

}

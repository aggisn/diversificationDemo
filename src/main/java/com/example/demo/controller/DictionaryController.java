package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.example.demo.demoCount.easyExcel.EasyExcelListener;
import com.example.demo.domain.DTO.DictionaryDTO;
import com.example.demo.domain.VO.DictionaryVO;
import com.example.demo.domain.param.DictionaryParam;
import com.example.demo.domain.request.DictionaryRequest;
import com.example.demo.entity.DictionaryEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.EarthquakeException;
import com.example.demo.mapper.DictionaryMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.DictionaryService;
import com.example.demo.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * 描述：
 *
 * @author zlj
 */
@Slf4j
@RestController
@RequestMapping("/api/do1/v1")
public class DictionaryController {
    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private DictionaryMapper dictionaryMapper;
    @Resource
    private UserMapper userMapper;


    private static final String SERVICE_NAME = "";


    /**
     * 新增
     *
     * @param request
     * @return DictionaryVO
     * @throws EarthquakeException
     */
    @PostMapping("/Dictionary1")
    public Result<DictionaryVO> insert(@Validated() @RequestBody DictionaryRequest request) {
        if (request != null) {
            request.getRemark();
            System.out.println(request.getAge());
            log.info(request.getRemark());
        }

         DictionaryDTO trans = DozerUtil.trans(request, DictionaryDTO.class);
        try {
            DictionaryDTO insert = dictionaryService.save(trans);
            DictionaryVO dictionaryVO = DozerUtil.trans(insert, DictionaryVO.class);
            return Result.ofSuccess(dictionaryVO, "新增成功");
        } catch (EarthquakeException e) {
            log.error(e.getMessage());
            return Result.ofFail(-1, SERVICE_NAME + e.getMessage());
        }
    }


    /**
     * 修改
     *
     * @param request
     * @param id
     * @return
     * @throws
     */

    @PutMapping("/Dictionary/{id}")
    public Result<DictionaryVO> update(@Validated() @RequestBody DictionaryRequest request, @PathVariable("id") Long id) {
        try {
            DictionaryDTO dto = DozerUtil.trans(request, DictionaryDTO.class);
            dto.setId(id);
            dto = dictionaryService.updateByPrimaryKey(dto);
            DictionaryVO dictionaryVO = DozerUtil.trans(dto, DictionaryVO.class);
            return Result.ofSuccess(dictionaryVO, "修改成功");
        } catch (EarthquakeException e) {
            log.error(e.getMessage());
            return Result.ofFail(-1, SERVICE_NAME + "修改失败," + e.getMessage());
        }
    }


    /**
     * 查看详情
     *
     * @param
     * @return
     * @throws
     */
    @GetMapping("/Dictionary/{id}")
    public Result<DictionaryVO> select(@PathVariable("id") Long id) {
        try {
            DictionaryDTO dto = dictionaryService.selectByPrimaryKey(id);
            DictionaryVO dictionaryVO = DozerUtil.trans(dto, DictionaryVO.class);
            return Result.ofSuccess(dictionaryVO, "查询成功");
        } catch (EarthquakeException e) {
            log.error(e.getMessage());
            return Result.ofFail(-1, SERVICE_NAME + "查询失败，" + e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/Dictionary/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        try {
            Boolean result = dictionaryService.deleteByPrimaryKey(id);
            return result ? Result.ofSuccess(result, "删除成功") : Result.ofFail(-1, "删除失败");
        } catch (EarthquakeException e) {
            log.error(e.getMessage());
            return Result.ofFail(-1, SERVICE_NAME + "删除失败，" + e.getMessage());
        }

    }


    /**
     * 查询列表
     *
     * @param param
     * @return
     * @throws
     */
    @PostMapping("/Dictionary/list")
    public Result<RequestMap> list(@Validated @RequestBody DictionaryParam param) {

        try {
            RequestMap requestMap = dictionaryService.selectByCondition(param);
            return Result.ofSuccess(requestMap, "查询成功");
        } catch (Exception e) {
            log.error("错误信息", e);
            return Result.ofFail(-1, SERVICE_NAME + "查询列表失败，" + e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/Dictionary/Import")
    @ResponseBody
    public String Import(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), DictionaryEntity.class, new EasyExcelListener(dictionaryMapper)).sheet().doRead();
        return "success";
    }


    @PostMapping("/Dictionary/ceshi")
    @ResponseBody
    public String Import1(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), UserEntity.class, new EasyExcelListener(userMapper)).sheet().doRead();
        return "success";
    }

    /**
     * 导出
     *
     * @param param
     * @param response
     * @throws
     */
    @PostMapping("/Dictionary/export")
    public void downloadFailedUsingJson(HttpServletResponse response, @RequestBody DictionaryParam param) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("字典项", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), DictionaryEntity.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(dictionaryService.data(param));
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }


    /**
     * 导出
     *
     * @param param
     * @param response
     * @throws
     */
    @PostMapping("/Dictionary/exports")
    public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
//        try {
//            response.setContentType("application/vnd.ms-excel");
//            response.setCharacterEncoding("utf-8");
//            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//            String fileName = URLEncoder.encode("字典项", "UTF-8").replaceAll("\\+", "%20");
//            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
//            EasyExcel.write(response.getOutputStream(), DictionaryEntity.class).autoCloseStream(Boolean.FALSE).sheet("模板")
//                    .doWrite(data());
//        } catch (Exception e) {
//            // 重置response
//            response.reset();
//            response.setContentType("application/json");
//            response.setCharacterEncoding("utf-8");
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("status", "failure");
//            map.put("message", "下载文件失败" + e.getMessage());
//            response.getWriter().println(JSON.toJSONString(map));
//        }

        // 输出流
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("字典项", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        HashMap<Integer, String[]> dropDownMap = new HashMap<>();
        // 指定下拉框
        String[] mineralWaterType = {"矿泉水;纯净水;饮料"};
        dropDownMap.put(4, mineralWaterType);
        EasyExcelUtil.writeExcelWithModel(response.getOutputStream(), DictionaryEntity.class, dropDownMap);

    }

    private Collection<?> data() {
       
        return null;
    }


    /**
     * 模板下载
     *
     * @param response
     * @return
     */
    @RequestMapping("/HdEventMajor/template")
    public Result<Boolean> template(HttpServletResponse response) {
        try {
            String exportFileName = SERVICE_NAME + "模板";
            exportFileName = URLEncoder.encode(exportFileName, Constant.Common.UTF8);
            Workbook workbook = dictionaryService.exportTemplate();
            response.setCharacterEncoding(Constant.Common.UTF8);
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            // 设置contentType为excel格式
            response.setHeader("Content-disposition", "attachment;filename=" + exportFileName + ".xls");
            //默认Excel名称
            response.flushBuffer();
            workbook.write(response.getOutputStream());
            return Result.ofSuccess(true, "调用" + SERVICE_NAME + "导出成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.ofFail(-1, "导出" + SERVICE_NAME + "失败，" + e.getMessage());
        }
    }


    /**
     * word模板读取填充
     */


}

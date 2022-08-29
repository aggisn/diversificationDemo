package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.example.demo.Exception.EarthquakeException;
import com.example.demo.demoCount.easyExcel.EasyExcelListener;
import com.example.demo.domain.DTO.DictionaryDTO;
import com.example.demo.domain.VO.DictionaryVO;
import com.example.demo.domain.param.DictionaryParam;
import com.example.demo.domain.request.DictionaryRequest;
import com.example.demo.entity.DictionaryEntity;
import com.example.demo.mapper.DictionaryMapper;
import com.example.demo.service.DictionaryService;
import com.example.demo.utils.DozerUtil;
import com.example.demo.utils.RequestMap;
import com.example.demo.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 描述：
 *
 * @author zlj
 */
@Slf4j
@RestController
@RequestMapping("/api/do/v1")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictionaryMapper dictionaryMapper;

    private static final String SERVICE_NAME = "";


    /**
     * 新增
     *
     * @param request
     * @return
     * @throws
     */

    @PostMapping("/Dictionary")
    public Result<DictionaryVO> insert(@Validated() @RequestBody DictionaryRequest request) {

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


}

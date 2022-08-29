package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.exception.EarthquakeException;
import com.example.demo.domain.DTO.DictionaryDTO;
import com.example.demo.domain.param.DictionaryParam;
import com.example.demo.entity.DictionaryEntity;
import com.example.demo.util.RequestMap;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlj
 * @since 2022-01-12
 */
public interface DictionaryService extends IService<DictionaryEntity> {

        /**
         * 插入记录
         *
         * @param record 记录
         * @return 插入条数
         * @throws
         */

    DictionaryDTO save(DictionaryDTO record) throws EarthquakeException;

        /**
         * 根据主键id获取记录
         *
         * @param id id
         * @return 记录
         * @throws
         */
    DictionaryDTO selectByPrimaryKey(Long id) throws EarthquakeException;

        /**
         * 根据主键id更新
         *
         * @param record 记录
         * @return 更新数
         * @throws
         */
    DictionaryDTO updateByPrimaryKey(DictionaryDTO record) throws EarthquakeException;

        /**
         * 根据主键id删除
         *
         * @param id id
         * @return 删除条数
         * @throws
         */
        Boolean deleteByPrimaryKey(Long id) throws EarthquakeException;

        /**
         * 根据条件获取集合
         *
         * @param param 条件类
         * @return 集合
         */
        RequestMap selectByCondition(DictionaryParam param) throws EarthquakeException;

        /**
         * 根据条件获取所有的对象集合
         * @param param 条件
         * @return 集合
         */
        List<DictionaryEntity> getAll(DictionaryParam param);



        /**
    * 导出excel表格
    * @param param
    * @return
    */
        List<DictionaryEntity> data(DictionaryParam param);


    Workbook exportTemplate();
}

package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.exception.EarthquakeException;
import com.example.demo.domain.DTO.UserDTO;
import com.example.demo.domain.param.UserParam;
import com.example.demo.entity.UserEntity;
import com.example.demo.util.RequestMap;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlj
 * @since 2022-01-14
 */
public interface UserService extends IService<UserEntity> {

        /**
         * 插入记录
         *
         * @param record 记录
         * @return 插入条数
         * @throws
         */

    UserDTO save(UserDTO record) throws EarthquakeException;

        /**
         * 根据主键id获取记录
         *
         * @param id id
         * @return 记录
         * @throws
         */
    UserDTO selectByPrimaryKey(String id) throws EarthquakeException;

    UserDTO selectByName(String name) throws EarthquakeException;

        /**
         * 根据主键id更新
         *
         * @param record 记录
         * @return 更新数
         * @throws
         */
    UserDTO updateByPrimaryKey(UserDTO record) throws EarthquakeException;

        /**
         * 根据主键id删除
         *
         * @param id id
         * @return 删除条数
         * @throws
         */
        Boolean deleteByPrimaryKey(String id) throws EarthquakeException;

        /**
         * 根据条件获取集合
         *
         * @param param 条件类
         * @return 集合
         */
        RequestMap selectByCondition(UserParam param);

        /**
         * 根据条件获取所有的对象集合
         * @param param 条件
         * @return 集合
         */
        List<UserEntity> getAll(UserParam param);



        /**
    * 导出excel表格
    * @param param
    * @return
    */
        List<UserEntity> data(UserParam param);

        }

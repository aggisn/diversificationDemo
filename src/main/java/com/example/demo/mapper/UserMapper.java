
package com.example.demo.mapper;

import com.example.demo.demoCount.easyExcel.Basemapper;
import com.example.demo.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlj
 * @since 2022-01-14
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<UserEntity>, Basemapper {

        int  batchInsert(List<T> list);

        }


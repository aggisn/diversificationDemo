package com.example.demo.demoCount.easyExcel;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author ZLJ
 * @description
 * @date 2021/9/23
 */
public interface Basemapper {

    int batchInsert(List<T> list);

}

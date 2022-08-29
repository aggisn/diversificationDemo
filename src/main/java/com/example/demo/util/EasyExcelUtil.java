package com.example.demo.util;

import com.alibaba.excel.EasyExcel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

/**
 * @author ZLJ
 * @description
 * @date 2022/6/16
 */
public class EasyExcelUtil {
    public static <T> void writeExcelWithModel(OutputStream outputStream, Class<T> clazz, Map<Integer,String[]> dropDownMap) throws IOException {
        EasyExcel.write(outputStream, clazz).registerWriteHandler(new TitleHandler(dropDownMap)).sheet("模板").doWrite(data());
    }

    private static Collection<?> data() {
        return null;
    }
}

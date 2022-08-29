package com.example.demo.demoCount.easyExcel;


import java.lang.annotation.*;

/**
 * @author 顾鹏飞
 * @date 2021/9/2
 * @description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface ExcelContentProperty {

    // 表头名称
    String tableHeader();

    int index() default -1;

    boolean required() default false;

    String format() default "yyyy-MM-dd";
}

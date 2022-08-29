package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_dictionary")
public class DictionaryEntity {


    @ExcelProperty("id")
    private Long id;
    @ExcelProperty("类型")
    private String dictionaryType;
    @ExcelProperty("代码")
    private String dictionaryCode;
    @ExcelProperty("名称")
    private String dictionaryName;
    @ExcelProperty("排序")
    private Integer sort;
    @ExcelProperty("备注")
    private String remark;

    @TableField(value = "created",fill = FieldFill.INSERT )
    @ExcelIgnore
    private Date created;
    @TableField(value = "updated",fill = FieldFill.INSERT_UPDATE)
    @ExcelIgnore
    private Date updated;


    @ExcelIgnore
    private Date deleted;
}
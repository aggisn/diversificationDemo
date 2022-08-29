package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class DictionaryEntity {


    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelIgnore()
    private Long id;
    /**
     * 企业名称
     */
    @ExcelProperty(value = "企业名称",index = 0)
    private String enterpriseName;
    /**
     * 企业位置（经纬度）
     */
    @ExcelProperty(value = "企业位置（经纬度）",index = 1)
    private String enterpriseAddress;
    /**
     * 负责人
     */
    @ExcelProperty(value = "负责人",index = 2)
    private String charger;
    /**
     * 负责人电话
     */
    @ExcelProperty(value = "负责人电话",index = 3)
    private String chargerTel;
    /**
     * 企业生产矿泉水类型 （多选）	            □矿泉水	            □纯净水	            □饮料
     */
    @ExcelProperty(value = "企业生产矿泉水类型（多选）",index = 4)
    private String mineralWaterType;
    /**
     * 企业年生产能力（吨每年）
     */
    @ExcelProperty(value = "企业年生产能力（吨每年）",index = 5)
    private BigDecimal throughput;
    /**
     * 城市矿泉水厂水量储备（升）
     */
    @ExcelProperty(value = "城市矿泉水厂水量储备（升）",index = 6)
    private BigDecimal waterReserve;
    /**
     * 单位负责人
     */
    @ExcelProperty(value = "单位负责人",index = 7)
    private String departmentCharger;
    /**
     * 填表人
     */
    @ExcelProperty(value = "填表人",index = 8)
    private String reporter;
    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话",index = 9)
    private String contactNumber;
    /**
     * 报出日期
     */
    @ExcelProperty(value = "报出日期",index = 10)
    private Date reportDate;
    /**
     * 创建人id
     */
    @ExcelIgnore
    private String creatorId;
    /**
     * 创建时间
     */
    @ExcelIgnore
    private Date created;
    /**
     * 更新时间
     */
    @ExcelIgnore
    private Date updated;
    /**
     * 状态（0：启用；1：删除）
     */
    @ExcelIgnore
    private Integer state;
}
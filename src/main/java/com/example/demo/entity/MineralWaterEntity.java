package com.example.demo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class MineralWaterEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业位置（经纬度）
     */
    private String enterpriseAddress;
    /**
     * 负责人
     */
    private String charger;
    /**
     * 负责人电话
     */
    private String chargerTel;
    /**
     * 企业生产矿泉水类型 （多选）	            □矿泉水	            □纯净水	            □饮料
     */
    private String mineralWaterType;
    /**
     * 企业年生产能力（吨每年）
     */
    private BigDecimal throughput;
    /**
     * 城市矿泉水厂水量储备（升）
     */
    private BigDecimal waterReserve;
    /**
     * 单位负责人
     */
    private String departmentCharger;
    /**
     * 填表人
     */
    private String reporter;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 报出日期
     */
    private LocalDate reportDate;
    /**
     * 创建人id
     */
    private String creatorId;
    /**
     * 创建时间
     */
    private LocalDateTime created;
    /**
     * 更新时间
     */
    private LocalDateTime updated;
    /**
     * 状态（0：启用；1：删除）
     */
    private Integer state;
}
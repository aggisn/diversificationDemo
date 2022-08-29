package com.example.demo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class HdYearNaturalDisastersEntity {


    /**
     * 主键id
     */
    private Long id;
    /**
     * 业务id
     */
    private Long yearDisastetId;
    /**
     * 年度自然灾害类型
     * 1:干旱
     * 2:洪涝
     * 3:台风
     * 4:风暴
     * 5:低温冷冻
     * 6:雪灾
     * 7:沙尘暴
     * 8:地震
     * 9:崩塌
     * 10:滑坡
     * 11:泥石流
     * 12:海洋
     * 13:森林草原火灾
     */
    private Integer disasterType;
    private String disasterTypeName;

    /**
     * 受灾状态
     */
    private Integer affectstate;

    private String affectstateName;
    /**
     * 受灾人口
     */
    private Integer disasterPopulation;
    /**
     * 死亡失踪人口
     */
    private Integer deadPopulation;
    /**
     * 农作物受灾面积
     */
    private BigDecimal cropsDisasterArea;
    /**
     * 农作物绝收面积
     */
    private BigDecimal cropsFailureArea;
    /**
     * 倒塌房屋户数
     */
    private Integer collapseHouseholdCount;
    /**
     * 倒塌房屋间数
     */
    private Integer collapseRoomCount;
    /**
     * 损坏房屋户数
     */
    private Integer damageHouseholdCount;
    /**
     * 损坏房屋间数
     */
    private Integer damageRoomCount;
    /**
     * 火场总面积
     */
    private BigDecimal fireArea;
    /**
     * 受害森林面积
     */
    private BigDecimal woodlandDisasterarea;
    /**
     * 受害草原面积
     */
    private BigDecimal grasslandDisasterarea;
    /**
     * 直接经济损失
     */
    private BigDecimal directEconomicLoss;
    /**
     * 填写说明
     */
    private String fillingInstructions;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 删除时间
     */
    private Date deleted;

    /**
     * 文件id
     */
    private List<String> filedIds;

}
package com.example.demo.util.newTM;

import lombok.Data;

/**
 * @author ZLJ
 * @description
 * @date 2022/5/24
 */
@Data
public class Person {
    /**
     * 真实姓名
     */
     @Sensitive(strategy = SensitiveStrategy.USERNAME)
    private String realName;
    /**
     * 地址
     */
    @Sensitive(strategy = SensitiveStrategy.ADDRESS)
    private String address;
    /**
     * 电话号码
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String phoneNumber;
    /**
     * 身份证号码
     */
    @Sensitive(strategy = SensitiveStrategy.ID_CARD)
    private String idCard;

}

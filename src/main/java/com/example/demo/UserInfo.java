package com.example.demo;

import com.example.demo.util.tm.Desensitized;
import com.example.demo.util.tm.SensitiveTypeEnum;
import lombok.Data;

/**
 * @author ZLJ
 * @description
 * @date 2022/5/24
 */
@Data
public class UserInfo {
    @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
    private String realName;

    @Desensitized(type = SensitiveTypeEnum.ADDRESS)
    private String name;

    @Desensitized(type = SensitiveTypeEnum.ID_CARD)
    private String idCardNo;

    @Desensitized(type = SensitiveTypeEnum.MOBILE_PHONE)
    private String mobileNo;

    @Desensitized(type = SensitiveTypeEnum.ADDRESS)
    private String address;

    @Desensitized(type = SensitiveTypeEnum.PASSWORD)
    private String password;

    @Desensitized(type = SensitiveTypeEnum.BANK_CARD)
    private String bankCard;
}

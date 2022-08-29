package com.example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZLJ
 * @description
 * @date 2022/5/25
 */
@Configuration
@ConfigurationProperties(prefix = "desensitized")
public class MyDesensitized {

    private static  String chineseNames;

    private String chineseName;

    private String idCardNum;

    private String fixedPhone;

    private String mobilePhone;

    private String address;

    private String email;

    private String bankCard;

    private String password;

    private String account;

    public MyDesensitized(String chineseName, String idCardNum, String fixedPhone, String mobilePhone, String address, String email, String bankCard, String password, String account) {
        this.chineseName = chineseName;
        this.idCardNum = idCardNum;
        this.fixedPhone = fixedPhone;
        this.mobilePhone = mobilePhone;
        this.address = address;
        this.email = email;
        this.bankCard = bankCard;
        this.password = password;
        this.account = account;
    }

    public MyDesensitized() {
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        if (StringUtils.isBlank(chineseName)) {
            this.chineseName = chineseName;
        }
        String name = StringUtils.left(chineseName, 1);

        this.chineseName =  StringUtils.rightPad(name, StringUtils.length(chineseName), "*");;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

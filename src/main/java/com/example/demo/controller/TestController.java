package com.example.demo.controller;

import com.example.demo.DFA.SensitiveWordFilter;
import com.example.demo.UserInfo;
import com.example.demo.util.tm.DesensitizedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZLJ
 * @description
 * @date 2022/5/24
 */
@RestController
public class TestController {

    @Autowired
    private DesensitizedUtils deens;

    @GetMapping("/test")
    public String test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress("上海浦东新区未知地址12号");
        userInfo.setName("烽火路连营未知地址12号TMD");
        userInfo.setIdCardNo("320382444474441411");
        userInfo.setMobileNo("13555551141");
        userInfo.setPassword("123456");
        userInfo.setRealName("张三");
        userInfo.setBankCard("111521444474441411");

        String json = deens.getJson(userInfo);

        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append("123TM,D123");

        String s = stringBuilder.toString();
        String result = null;
        result = SensitiveWordFilter.Filter(s);

        return json;
    }


}

package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author ZLJ
 * @description
 * @date 2022/5/24
 */
@Configuration
@ConfigurationProperties(prefix = "test")
public class MyProperties {

    private static boolean flag;

    private Boolean enable;

    private List<String> list;

    public MyProperties() {
    }

    public MyProperties(Boolean enable, List<String> list) {
        this.enable = enable;
        this.list = list;
    }

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        MyProperties.flag = flag;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
        MyProperties.flag = enable;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}

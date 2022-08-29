package com.example.demo.demoCount.getOpenid;

import lombok.Data;

/**
 * @author ZLJ
 * @description
 * @date 2022/7/8
 */
@Data
public class GetOpenidRequest {


   private String code;
    private String appid;
    private String secret;
}

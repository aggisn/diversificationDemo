package com.example.demo.demoCount.getOpenid;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * @author ZLJ
 * @description
 * @date 2022/7/8
 */
@RestController
public class GetOpenid {

    @GetMapping(value = "/login")
    public Result Login(@RequestBody GetOpenidRequest request) {
        GetOpenIdUtil getOpenIdUtil=new GetOpenIdUtil();
        String jsonId = getOpenIdUtil.getopenid(request.getAppid(),
                request.getCode(),
                request.getSecret());

        JSONObject jsonObject = JSONObject.parseObject(jsonId);

        //可将返回值类型改为String，然后直接return jsonObject
        return Result.ofSuccess(jsonObject);
    }


    @GetMapping(value = "/getOpenid")
    public Result Login(@RequestParam("appid") String appid ,@RequestParam("js_code") String js_code , @RequestParam("secret") String secret) {
        GetOpenIdUtil getOpenIdUtil=new GetOpenIdUtil();
        String jsonId = getOpenIdUtil.getopenid(appid,
                js_code,
                secret);

        JSONObject jsonObject = JSONObject.parseObject(jsonId);

        //可将返回值类型改为String，然后直接return jsonObject
        return Result.ofSuccess(jsonObject);
    }
}

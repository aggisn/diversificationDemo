package com.example.demo.demoCount.api;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname WeatherController
 * @Date 2022/9/6 10:47
 * @Author: zlj
 */
@RestController
@Slf4j
public class WeatherController {


    /**
     * 远程调用百度天气接口
     * @param ak
     * @param districtId
     * @return
     */
    @GetMapping(value = "/weather")
    public JSONObject Login(@RequestParam("ak") String ak , @RequestParam("districtId") String districtId ) {

       log.info(ak);
       log.info(districtId);
        WeatherApi weatherApi = new WeatherApi();
        String weather = weatherApi.getWeather(ak, districtId);


        JSONObject jsonObject = JSONObject.parseObject(weather);

        //可将返回值类型改为String，然后直接return jsonObject
        return jsonObject;
    }
}

package com.example.demo.demoCount.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Classname WeatherApi
 * @Date 2022/9/6 10:40
 * @Author: zlj
 */
public class WeatherApi {

    public String getWeather(String ak, String districtId) {
        BufferedReader in = null;
        String url = "https://api.map.baidu.com/weather/v1/?district_id=" +
                districtId +
                "&output=JSON&ak=" +
                ak +
                "&data_type=all";

        try {
            URL weChatUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = weChatUrl.openConnection();

            // 设置通用的请求属性
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}

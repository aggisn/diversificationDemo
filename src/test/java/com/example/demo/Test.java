package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserEntity;

import java.util.ArrayList;

/**
 * @author ZLJ
 * @description
 * @date 2022/5/24
 */

public class Test {

    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";


    public static void main(String[] args) {

        String rockLayer ="[{\"dcxh\":\"1\",\"dcbh\":\"\",\"dcnd\":\"\",\"sdcy\":\"\",\"cdsd\":\"\",\"fchd\":\"\",\"yttz\":\"\"},{\"dcxh\":\"2\",\"dcbh\":\"\",\"dcnd\":\"\",\"sdcy\":\"\",\"cdsd\":\"\",\"fchd\":\"\",\"yttz\":\"\"}]";



        JSONArray objects = JSONObject.parseArray(rockLayer);
        for (Object object : objects) {
            JSONObject jsonObject = (JSONObject) object;
//            System.out.println(jsonObject.getString("dcxh"));

        }

        ArrayList<UserEntity> strataRockInformationEntitie = new ArrayList<>();
        UserEntity rockLayerEntity = new UserEntity();
        rockLayerEntity.setName("1");
        rockLayerEntity.setId("2");
        rockLayerEntity.setAge(3);
        rockLayerEntity.setPassword("3");

        strataRockInformationEntitie.add(rockLayerEntity);

        String s = JSONObject.toJSONString(strataRockInformationEntitie);
        System.out.println(s);


    }
}

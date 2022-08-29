package com.example.demo.Thread;

import org.springframework.beans.factory.annotation.Value;

import java.util.*;

/**
 * @author ZLJ
 * @description
 * @date 2022/6/30
 */
public class CcTest {


    /**
     * 文件上传存储地址
     */
    @Value("${file.upload.path}")
    private static String fileUploadPath;
    public static void main(String[] args) {

       Map map = new HashMap<>();

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("1");
        arrayList.add("1");
        arrayList.add("1");

        RequestMap requestMap = new RequestMap((long) 50,1,50,1, list);

        map.put("1", requestMap);
        map.put("2",arrayList);


        System.out.println(map);





    }
}

package com.example.demo.util;



import org.apache.commons.lang3.StringUtils;

import static java.lang.Integer.parseInt;

/**
 * @author ZLJ
 * @description
 * @date 2022/4/22
 */
public class IntegerUtils{




    //空字符串处理
    public static Integer valueOf(String s){

        if (StringUtils.isBlank(s)){
            return null;
        }

        return Integer.valueOf(parseInt(s, 10));
    }
}

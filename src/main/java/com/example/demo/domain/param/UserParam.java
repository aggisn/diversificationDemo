package com.example.demo.domain.param;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.util.PageBaseParam;
import lombok.Data;


@Data
@TableName("t_user")
public class UserParam extends PageBaseParam {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;
    private String password;
}
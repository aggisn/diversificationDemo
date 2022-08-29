package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("t_user")
public class UserEntity {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private Integer age;
    private String password;
}
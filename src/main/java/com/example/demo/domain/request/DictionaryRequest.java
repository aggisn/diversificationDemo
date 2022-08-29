package com.example.demo.domain.request;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_dictionary")
public class DictionaryRequest {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String dictionaryType;
    private String dictionaryCode;
    private String dictionaryName;

    private Integer age = 10;

    private String remark ="山东吧卡的";
    private Date createTime;
    private Date deleteTime;
    private Date updateTime;
}
package com.example.demo.domain.VO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_dictionary")
public class DictionaryVO {

    private static final long serialVersionUID = 1L;
    private String id;
    private String dictionaryType;
    private String dictionaryCode;
    private String dictionaryName;
    private Integer sort;
    private String remark;
    private Date createTime;
    private Date deleteTime;
    private Date updateTime;
}
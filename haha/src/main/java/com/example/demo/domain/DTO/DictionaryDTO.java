package com.example.demo.domain.DTO;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
@TableName("t_dictionary")
public class DictionaryDTO {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String dictionaryType;
    private String dictionaryCode;
    private String dictionaryName;
    private Integer sort;
    private String remark;
    private Date createTime;
    private Date deleteTime;
    private Date updateTime;
}
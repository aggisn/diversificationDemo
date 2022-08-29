package com.example.demo.domain.param;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.util.PageBaseParam;
import lombok.Data;


@Data
@TableName("t_dictionary")
public class DictionaryParam extends PageBaseParam {


    private String dictionaryType;

    private String dictionaryName;

}
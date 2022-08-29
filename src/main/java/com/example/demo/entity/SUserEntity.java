package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zlj
 * @since 2022-06-13
 */
@Getter
@Setter
  @TableName("t_s_user")
public class SUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("id")
      private Integer id;

    @TableField("`name`")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("mobilePhone")
    private String mobilePhone;

    @TableField("idcard")
    private String idcard;

    @TableField("ip")
    private String ip;


}

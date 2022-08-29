package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zlj
 * @since 2022-05-16
 */
@Getter
@Setter
  @TableName("t_rotation")
public class RotationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 主键id
     */
      @TableField("id")
    private String id;

      /**
     * 行政区划id
     */
      @TableField("division_id")
    private String divisionId;

      /**
     * 图片名称
     */
      @TableField("picture_name")
    private String pictureName;

      /**
     * 图片路径
     */
      @TableField("picture_path")
    private String picturePath;

      /**
     * 排序
     */
      @TableField("sort")
    private Integer sort;

      /**
     * 发布时间
     */
      @TableField("release_time")
    private LocalDateTime releaseTime;

      /**
     * 修改时间
     */
        @TableField(value = "updated", fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updated;


}

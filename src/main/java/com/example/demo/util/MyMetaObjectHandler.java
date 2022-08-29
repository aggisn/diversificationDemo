package com.example.demo.util;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ZLJ
 * @description
 * @date 2021/9/17
 * <p> MyBatisPlus自定义字段自动填充处理类 - 实体类中使用 @TableField注解 </p>
 *      *
 *      * @description: 注意前端传值时要为null
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        //创建时间为系统时间
        this.setFieldValByName("create_time",new Date(), metaObject);
        //更新时间为系统时间
        this.setFieldValByName("update_time",new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("update_time",new Date(), metaObject);
    }
}




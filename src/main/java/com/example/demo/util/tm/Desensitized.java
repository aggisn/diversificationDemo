package com.example.demo.util.tm;

import java.lang.annotation.*;

/**
 * @author ZLJ
 * @description 脱敏注解
 * @date 2022/5/24
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desensitized {
    /*脱敏类型(规则)*/
    SensitiveTypeEnum type();

    /*判断注解是否生效的方法*/
    String isEffictiveMethod() default "";
}

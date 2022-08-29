package com.example.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZLJ
 * @description
 * @date 2022/5/25
 */
@Data
@ConfigurationProperties("aa")
public class DesensitizedProperties {

    private Integer a;
}

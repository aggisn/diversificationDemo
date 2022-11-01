package com.example.demo.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZLJ
 * @description
 * @date 2022/9/5
 */
@Data
public class TestEntity implements Serializable {
    private Integer id;
    private String name;
    private String parentId;
    private List<TestEntity> children;

    public TestEntity() {
    }

    ;

    public TestEntity(Integer id, String name, String parentId, List<TestEntity> children) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.children = children;
    }

}


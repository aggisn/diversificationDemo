package com.example.demo.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZLJ
 * @description
 * @date 2022/9/5
 */
@RestController
public class TreeTest {
    private static final String json = "["
            + "{\"id\":44116841,\"name\":\"测试部\",\"parentId\":56188932},"
            + "{\"id\":56104925,\"name\":\"飞轮储能事业部\",\"parentId\":null},"
            + "{\"id\":56121882,\"name\":\"高速电机部\",\"parentId\":56104925},"
            + "{\"id\":44941198,\"name\":\"项目管理部\",\"parentId\":null},"
            + "{\"id\":35232631,\"name\":\"视觉算法\",\"parentId\":19693688},"
            + "{\"id\":17387338,\"name\":\"人力行政部\",\"parentId\":null},"
            + "{\"id\":35197588,\"name\":\"行政\",\"parentId\":17387338},"
            + "{\"id\":19693688,\"name\":\"人工智能部\",\"parentId\":56188932},"
            + "{\"id\":35193567,\"name\":\"人力资源\",\"parentId\":17387338},"
            + "{\"id\":56122856,\"name\":\"结构部\",\"parentId\":56104925},"
            + "{\"id\":54197060,\"name\":\"系统平台部\",\"parentId\":56188932},"
            + "{\"id\":17387341,\"name\":\"财务部\",\"parentId\":null},"
            + "{\"id\":56188932,\"name\":\"机器人事业部\",\"parentId\":null},"
            + "{\"id\":35203611,\"name\":\"自然语言算法\",\"parentId\":19693688}]";

    @PostMapping("/tree/test")
    public List<TestEntity> list(){
        List<TestEntity> testEntitys = JSON.parseObject(json, new TypeReference<List<TestEntity>>(){});
        List<TestEntity> testEntities = TreeUtils.buildByRecursive(testEntitys);
        return testEntities;
    }

}

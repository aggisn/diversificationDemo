package com.example.demo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZLJ
 * @description
 * @date 2022/9/5
 */
public class TreeUtils {

    /**
     * 使用递归方法建树
     * @param
     * @return
     */
    public static List<TestEntity> buildByRecursive(List<TestEntity> testEntities) {
        List<TestEntity> trees = new ArrayList<>();
        for (TestEntity testEntity : testEntities) {
            if ("".equals(testEntity.getParentId()) || testEntity.getParentId() == null ) {
                trees.add(findChildren(testEntity,testEntities));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param
     * @return
     */
    public static TestEntity findChildren(TestEntity testEntity,List<TestEntity> testEntitys) {
        for (TestEntity projectBasicInfoDTO2 : testEntitys) {
            if(String.valueOf(testEntity.getId()).equals(projectBasicInfoDTO2.getParentId())) {
                if(testEntity.getChildren() == null) {
                    testEntity.setChildren(new ArrayList<>());
                }
                //是否还有子节点，如果有的话继续往下遍历，如果没有则直接返回
                testEntity.getChildren().add(findChildren(projectBasicInfoDTO2,testEntitys));
            }
        }
        return testEntity;
    }

}

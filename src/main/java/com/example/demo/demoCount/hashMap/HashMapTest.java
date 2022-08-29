package com.example.demo.demoCount.hashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

    /**
     * 原因：
     * map 里存的是 {@link HashMap.Node}，存有 key、value、next 还有 hash。
     * 存入（{@link HashMap#put(Object, Object)}）的时候，hash 和 key 都是一并存入的。这里改变了 key 但没有改变 hash。
     * 而获取（{@link HashMap#get(Object)}）的时候，是根据 key + hash 获取，必须二者都相等才行。
     * 这种方式防止了因为 key 发生更改造成的冲突的可能（hashCode 和 equals 都相等）。
     * <p>
     * 所以，最好不要让可变的实体作为键，以免发生这种 bug。
     * 要不然就创建一个该实体的副本作为键，反正确保其作为键的同时，不发生更改。
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<Person, Integer> map = new HashMap<>();
        Person p1 = new Person("ys", 12);
        System.out.println("p1 hashcode: " + p1.hashCode());
        Person p2 = new Person("ys", 24);
        System.out.println("p2 hashcode: " + p2.hashCode());
        map.put(p1, p1.getAge());
        map.put(p2, p2.getAge());
        map.forEach((k, v) -> {
                    System.out.println();
                    System.out.println(k + ":" + v);
                }
        );

        // 此时 p1 的 hashcode 变了，和 p1 一样
        p1.setAge(24);
        System.out.println("==== p1 更改，和 p2 一样 ====");
        map.forEach((k, v) -> System.out.println(k + ":" + v));
        // 发现输出是 p2
        System.out.println(map.get(p1));

        // 注意，这里获取的是 null，而不是 12
        Person p3 = new Person("ys", 12);
        System.out.println("==== new p3 与原先的 p1 一样 ====");
        System.out.println("p3 hashcode: " + p3.hashCode());
        System.out.println(map.containsKey(p3));
        System.out.println(map.get(p3));
        // 获取的是 24
        System.out.println("==== new p4 与 p2 一样====");
        Person p4 = new Person("ys", 24);
        System.out.println("p4 hashcode: " + p4.hashCode());
        System.out.println(map.containsKey(p4));
        System.out.println(map.get(p4));

        System.out.println("==== 遍历 ====");
        Set<Map.Entry<Person, Integer>> entries = map.entrySet();
        entries.forEach(personIntegerEntry -> {
            System.out.println(personIntegerEntry.getKey());
            System.out.println(personIntegerEntry.hashCode());
            System.out.println(personIntegerEntry.getValue());
        });

        // 如何获取原先 p1 对应的值
        // 基本只能改回 p1 了，因为存的 p1 和存的 hash 不一致了。除非发生特例。
    }
}
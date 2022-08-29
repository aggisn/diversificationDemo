package com.example.demo.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhj
 * @description
 * @date 2020/05/19 16:05
 */
public class DozerUtil {

    private DozerUtil(){}

    static Mapper mapper = new DozerBeanMapper();

    public static <D, E> E trans(D t, Class<E> clazz) {
        if (t == null) {
            return null;
        }
        return mapper.map(t, clazz);
    }

    public static <D, E> List<E> trans(D[] ts, Class<E> clazz) {
        List<E> es = new ArrayList<E>();
        if (ts == null) {
            return es;
        }

        for (D d : ts) {
            E e =  trans(d, clazz);
            if (e != null) {
                es.add(e);
            }
        }

        return es;
    }


    public static <D, E> List<E> trans(List<D> ts, Class<E> clazz) {
        List<E> es = new ArrayList<E>();
        if (ts == null) {
            return es;
        }
        for (D d : ts) {
            E e =  trans(d, clazz);
            if (e != null) {
                es.add(e);
            }
        }
        return es;
    }

}


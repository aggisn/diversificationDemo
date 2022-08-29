package com.example.demo.util;

/**
 * 常量定义
 *
 * @author dingshulin
 * @date 2019/3/22
 * 修改内容：
 */
public interface Constant
{
    /**
     * 通用的常量
     */
    class Common
    {
        /**
         * 无实际意义，用于判断
         */
        public static final int ZERO = 0;

        /**
         * 无实际意义，用于判断
         */
        public static final int ONE = 1;

        /**
         * 无实际意义，用于判断
         */
        public static final int TWO = 2;

        /**
         * 无实际意义，用于判断
         */
        public static final String ALL = "all";

        public static final String UTF8 = "UTF-8";
    }


    /**
     * shiro中的判断
     */
    class Shiro
    {
        /**
         * 缓存名称
         */
        public static final String SHURO_CACHENAME = "es";

        /**
         * md5加密方式
         */
        public static final String MD5 = "md5";
    }


    /**
     * 浏览器的判断
     */
    class Browser
    {
        /**
         * 火狐
         */
        public static final String FF = "FF";

        /**
         * IE
         */
        public static final String IE = "IE";

        /**
         * safari
         */
        public static final String SF = "SF";
    }


    /**
     * 系统参数key
     */
    class Parameter
    {
        /**
         * 是否是在线地图
         */
        public static final String PARAMETER_ISONLINE = "isOnline";

        /**
         * 中心点
         */
        public static final String PARAMETER_CENTER_POINT= "centerPoint";

        /**
         * 默认地图层级
         */
        public static final String PARAMETER_DEFAULT_LEVEL= "defaultLevel";
    }
}

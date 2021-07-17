package com.blog.common.util;

public class StringUtils {

//    判断当前字符串非空
    public static boolean isEmpty(String value) {
        if (null == value)
            return true;
        return value.isEmpty();
    }
}

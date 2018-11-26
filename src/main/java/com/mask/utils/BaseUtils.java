package com.mask.utils;

import java.util.List;

public class BaseUtils {

    /**
     * 比较两个字符串是否相同
     *
     * @param str_1 str_1
     * @param str_2 str_2
     * @return 是否相同
     */
    public static boolean equalsString(CharSequence str_1, CharSequence str_2) {
        return equalsObject(str_1, str_2, true);
    }

    /**
     * 比较两个Object是否相同
     *
     * @param obj_1           obj_1
     * @param obj_2           obj_2
     * @param isEqualsAllNull 全部为Null是否相同
     * @return 是否相同
     */
    public static boolean equalsObject(Object obj_1, Object obj_2, boolean isEqualsAllNull) {
        if (obj_1 == null && obj_2 == null) {
            if (isEqualsAllNull) {
                return true;
            } else {
                return false;
            }
        }
        if (obj_1 != null) {
            return obj_1.equals(obj_2);
        } else {
            return false;
        }
    }

    /**
     * String是否为空
     *
     * @param str str
     * @return 是否为空
     */
    public static boolean isEmptyString(CharSequence str) {
        return str == null || str.length() == 0 || "".contentEquals(str);
    }

    /**
     * List是否为空
     *
     * @param list list
     * @return 是否为空
     */
    public static boolean isEmptyList(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 获取List Size
     *
     * @param list list
     * @return List Size
     */
    public static int getListSize(List list) {
        return list == null ? 0 : list.size();
    }

    /**
     * Array是否为空
     *
     * @param array array
     * @return 是否为空
     */
    public static <T> boolean isEmptyArray(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 获取Array Length
     *
     * @param array array
     * @return List Length
     */
    public static <T> int getArrayLength(T[] array) {
        return array == null ? 0 : array.length;
    }

}

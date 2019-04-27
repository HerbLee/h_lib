/**
 * @Copyright: haitun
 */
package com.herb2sy.utils;

import com.herb2sy.exception.StringTransformationException;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.UUID;

/**
 * @des: 字符串相关操作控制类
 * @author: HerbLee
 * @email: herb2sy@gmail.com
 * @data: 2019/4/26 11:58 PM
 */


public class StringUtils {

    /**
     * obj转为字符串
     * @param obj 传入的obj类型
     * @return 字符串或者null
     */
    public static String obj2Str(Object obj){
        if (obj == null){
            return null;
        }
        try {
            return URLDecoder.decode(obj.toString().trim(), "UTF-8");
        }catch (Exception e){
            throwException(e.getMessage());
        }
        return null;

    }


    /**
     *  obj转字符串
     * @param obj obj类型
     * @param defVal 默认值
     * @return 正常转换或返回默认类型
     */
    public static String obj2Str(Object obj,String defVal){
        String s = obj2Str(obj);
        if (s == null){
            return defVal;
        }
        return s;
    }



    /**
     * obj转Integer 包括null
     * @param obj
     * @return 原来内容或者null
     */
    public static Integer obj2Int(Object obj) {
        Integer iRet = null;
        try {
            if (obj == null)
                return iRet;
            else
                iRet = (int) Integer.valueOf(obj.toString());
        } catch (Exception ex) {
            throwException(ex.getMessage());

        }
        return iRet;
    }

    /**
     * obj转Integer 如果为null 或者异常，为def
     * @param obj
     * @param def
     * @return
     */
    public static int obj2Int(Object obj, Integer def) {
        Integer i = obj2Int(obj);
        if (i == null){
            return def;
        }
        return i;
    }


    /**
     * obj转long类型
     * @param obj
     * @return 默认为null
     */
    public static Long obj2Long(Object obj){
        if (obj == null){
            return null;
        }
        try {
            return Long.valueOf(obj2Str(obj));
        }catch (Exception e){
            throwException(e.getMessage());

        }
        return null;
    }

    /**
     * obj 转Long类型
     * @param obj
     * @param def
     * @return
     */
    public static Long obj2Long(Object obj,Long def){
        Long aLong = obj2Long(obj);
        if (aLong == null){
            return def;
        }
        return aLong;
    }

    /**
     * obj 转float
     * @param obj
     * @return
     */
    public static Float obj2Float(Object obj){
        if (obj == null){
            return null;
        }
        try {
            return Float.valueOf(obj2Str(obj));
        }catch (Exception e){
            throwException(e.getMessage());
        }
        return null;
    }

    /**
     * obj 转float
     * @param obj
     * @param defValue
     * @return
     */
    public static Float obj2Float(Object obj, Float defValue){
        Float aFloat = obj2Float(obj);
        if (aFloat == null){
            return defValue;
        }
        return aFloat;
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if (str == null || str == "undefined" || str == "null" || str.length() == 0){
            return true;
        }
        return false;
    }

    /**
     * 判断多个字符串是否为空
     * @param strs
     * @return 传入的所有字符串都不为空返回false，有一个为空则返回true
     */
    public static boolean isEmptys(String... strs){

       for (String str:strs){
           if (isEmpty(str)){
               return true;
           }
       }
       return false;

    }

    /**
     * 判断obj是否为空
     * @param object
     * @return
     */
    public static boolean isNull(Object object){
        if (object != null){
            return false;
        }
        return true;
    }

    /**
     * 判断多个数据是否都不为空
     * @param objects
     * @return
     */
    public static boolean isNullS(Object... objects){
        for (Object item: objects) {
            if (isNull(item)){
                return true;
            }
        }
        return false;
    }


    private static void throwException(String msg){
        try {
            throw new StringTransformationException(obj2Str(msg,"数据错误"));
        } catch (StringTransformationException e1) {
            e1.printStackTrace();
        }
    }






}

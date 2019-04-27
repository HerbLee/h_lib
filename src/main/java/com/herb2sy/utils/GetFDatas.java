/**
 * @Copyright: Herb2Sy
 */
package com.herb2sy.utils;

import java.util.UUID;

/**
 * @des: 获取有格式的数据
 * @author: HerbLee
 * @email: herb2sy@gmail.com
 * @data: 2019/4/27 
 */
public class GetFDatas {
    /**
     * 获取32全球唯一码
     * @return
     */
    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}

    
package com.herb2sy.utils;
import lombok.Getter;

/**
 * @author: HerbLee
 * @date: Created on 2019/8/5 11:57
 * @function: 返回代码源
 */

@Getter
public enum  ResultCode {


    SUCCESS(200,"网络访问成功"),//成功返回

    LOGINSUCCESS(201,"登陆成功"),

    EXCEPTION(500,"出现异常"),//出现异常

    TOKEN_TIMEOUT(401,"登陆过期，请重新登陆"),//token过期

    ERROR(400,"网络请求出错");//失败返回


    private final int code;
    private final String desc;

    ResultCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
}

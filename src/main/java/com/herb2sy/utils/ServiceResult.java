    
package com.herb2sy.utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;


/**
 * @author: HerbLee
 * @date: Created on 2019/8/5 12:06
 * @function: 服务端返回类型
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServiceResult<T> implements Serializable {

    private String message;
    private int code;
    private T data;

    private ServiceResult(int code){
        this.code = code;
    }
    private ServiceResult(int code, T data){
        this.code = code;
        this.data = data;
    }
    private ServiceResult(int code, String message){
        this.code = code;
        this.message = message;
    }
    private ServiceResult(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccesss(){
        return this.code == 200;
    }

    public static <T> ServiceResult<T> createBySuccess(){
        return new ServiceResult<T>(200);
    }
    public static <T> ServiceResult<T> createBySuccess(String message){
        return new ServiceResult<T>(200,"访问成功");
    }
    public static <T> ServiceResult<T> createBySuccess(T data){
        return new ServiceResult<T>(200,"访问成功", data);
    }
    public static <T> ServiceResult<T> loginSuccess(HttpServletResponse response,T data){

        response.setStatus(HttpServletResponse.SC_CREATED);
        return new ServiceResult<T>(201,"登录成功",data);
    }

    public static <T> ServiceResult<T> createBySuccess(String msg, T data){
        return new ServiceResult<T>(200, msg, data);
    }

    public static <T> ServiceResult<T> createByError(HttpServletResponse response,String msg){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ServiceResult<T>(200,msg);
    }
    public static <T> ServiceResult<T> createByError(String msg){
        return new ServiceResult<T>(400,msg);
    }

    public static <T> ServiceResult<T> createByError(HttpServletResponse response, int code, String msg){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ServiceResult<T>(code,msg);
    }

    public static <T> ServiceResult<T> createByException(HttpServletResponse response, String msg){
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ServiceResult<T>(500,msg);
    }

    public static <T> ServiceResult<T> createByToken(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return new ServiceResult<T>(401,"登录已过期请重新登陆");
    }

}

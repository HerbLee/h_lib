package cn.artviewer.pay.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import lombok.Data;

/**
 * @author: HerbLee
 * @date: Created on 2019/8/5 12:06
 * @function: 服务端返回类型
 */
@Data
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
        return this.code == ResultCode.SUCCESS.getCode();
    }

    public static <T> ServiceResult<T> createBySuccess(){
        return new ServiceResult<T>(ResultCode.SUCCESS.getCode());
    }
    public static <T> ServiceResult<T> createBySuccess(String message){
        return new ServiceResult<T>(ResultCode.SUCCESS.getCode(), message);
    }
    public static <T> ServiceResult<T> createBySuccess(T data){
        return new ServiceResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.LOGINSUCCESS.getDesc(), data);
    }
    public static <T> ServiceResult<T> loginSuccess(HttpServletResponse response,T data){

        response.setStatus(HttpServletResponse.SC_CREATED);
        return new ServiceResult<T>(ResultCode.LOGINSUCCESS.getCode(),ResultCode.LOGINSUCCESS.getDesc(),data);
    }

    public static <T> ServiceResult<T> createBySuccess(String msg, T data){
        return new ServiceResult<T>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServiceResult<T> createByError(HttpServletResponse response,String msg){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ServiceResult<T>(ResultCode.ERROR.getCode(),msg);
    }
    public static <T> ServiceResult<T> createByError(String msg){
        return new ServiceResult<T>(ResultCode.ERROR.getCode(),msg);
    }

    public static <T> ServiceResult<T> createByError(HttpServletResponse response, int code, String msg){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ServiceResult<T>(code,msg);
    }

    public static <T> ServiceResult<T> createByException(HttpServletResponse response, String msg){
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ServiceResult<T>(ResultCode.EXCEPTION.getCode(),msg);
    }

    public static <T> ServiceResult<T> createByToken(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return new ServiceResult<T>(ResultCode.TOKEN_TIMEOUT.getCode(),ResultCode.TOKEN_TIMEOUT.getDesc());
    }

}
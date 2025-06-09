package com.zhangjun.excel.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回对象
 * @Author zhangjun
 * @Date 2025/4/22 17:09
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult <T>{
    private boolean success;
    private  long code;
    private  String message;
    private  T data;

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(true,ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 成功返回结果
     * @param data  获取的数据
     * @param message   提示信息
     * @return
     * @param <T>
     */
    public static <T>  CommonResult<T> success(T data, String message){
        return new CommonResult<T>(true,ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode){
        return new CommonResult<T>(false,errorCode.getCode(),errorCode.getMessage(),null);
    }

    /**
     * 失败返回结果
     * @param message   提示信息
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<T>(false,ResultCode.FAILED.getCode(),message,null);
    }

    /**
     * 参数验证失败返回结果
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> failed(){
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     *参数验证失败返回结果
     * @param message 提示信息
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> validateFailed(String message){
        return new CommonResult<T>(false,ResultCode.VALIDATE_FAILED.getCode(),message,null);
    }

    /**
     * 未登录返回结果
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> unauthorized(T data){
        return new CommonResult<T>(false,ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> forbidden(T data){
        return new CommonResult<T>(false,ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMessage(),data);
    }

    /**
     * 用户名或者密码错误
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> invalidUsernameorPassword(T data){
        return new CommonResult<T>(false,ResultCode.INVALID_USERNAME_OR_PASSWORD.getCode(),ResultCode.INVALID_USERNAME_OR_PASSWORD.getMessage(),data);
    }


    /**
     * 用户名或密码为空
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> emptyUsernameorPassword(T data){
        return new CommonResult<T>(false,ResultCode.EMPTY_USERNAME_OR_PASSWORD.getCode(),ResultCode.EMPTY_USERNAME_OR_PASSWORD.getMessage(),data);
    }

    /**
     * 业务层返回错误
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> applicationError(T data){
        return new CommonResult<T>(false,ResultCode.APPLICATION_ERROR.getCode(),ResultCode.APPLICATION_ERROR.getMessage(),data);
    }
}

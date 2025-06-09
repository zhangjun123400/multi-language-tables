package com.zhangjun.excel.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author zhangjun
 * @Date 2025/4/22 17:11
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum ResultCode implements IErrorCode {
    SUCCESS(200,true,"操作成功"),
    FAILED(500,false,"操作失败"),
    INVALID_USERNAME_OR_PASSWORD(501,false,"用户名或者密码错误"),
    VALIDATE_FAILED(404,false, "参数检验失败"),
    UNAUTHORIZED(401, false,"暂未登录或token已经过期"),
    FORBIDDEN(403, false,"没有相关权限"),
    APPLICATION_ERROR(600,false,"业务层返回错误"),
    EMPTY_USERNAME_OR_PASSWORD(502,false,"用户名或者密码为空");

   private long code ;

   private boolean success ;

   private String message;

}

package com.zhangjun.excel.common.api;

/**封装API的错误码
 * @Author zhangjun
 * @Date 2025/4/22 17:12
 * @Version 1.0
 */
public interface IErrorCode {

    long getCode();

    String getMessage();

    boolean isSuccess();
}

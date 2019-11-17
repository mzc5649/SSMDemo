package com.mzc.exception;

/**
 * 1自定义异常类
 * 2编写异常处理器HandlerExceptionResolver
 * 3
 */
public class SysException extends Exception {
    //存储提示信息
    private String message;

    public SysException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

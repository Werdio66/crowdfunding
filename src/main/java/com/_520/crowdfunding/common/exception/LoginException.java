package com._520.crowdfunding.common.exception;

/**
 *  处理登录的异常
 */
public class LoginException extends RuntimeException {

    public LoginException() {

    }
    // 将异常信息传递给父类
    public LoginException(String msg){
        super(msg);
    }
}

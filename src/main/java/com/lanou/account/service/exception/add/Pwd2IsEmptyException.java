package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class Pwd2IsEmptyException extends AddAccountException {
    @Override
    public String getMessage() {
        return "再次输入密码为空";
    }
}

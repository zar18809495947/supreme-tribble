package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class Pwd2NotIsPwdException extends AddAccountException {
    @Override
    public String getMessage() {
        return "两次密码匹配不上";
    }
}

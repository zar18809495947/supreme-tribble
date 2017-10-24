package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class LoginNameFormatErrorException extends AddAccountException {
    @Override
    public String getMessage() {
        return "30长度以内的字母、数字和下划线的组合";
    }
}

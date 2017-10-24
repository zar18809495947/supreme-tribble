package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class TelFormatErrorException extends AddAccountException {
    @Override
    public String getMessage() {
        return "电话号格式错误";
    }
}

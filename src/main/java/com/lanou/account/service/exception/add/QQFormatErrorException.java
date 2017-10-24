package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class QQFormatErrorException extends AddAccountException {
    @Override
    public String getMessage() {
        return "qq格式错误";
    }
}

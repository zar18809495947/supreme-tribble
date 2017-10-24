package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class ZipFormatErrorException extends AddAccountException {
    @Override
    public String getMessage() {
        return "邮编格式错误";
    }
}

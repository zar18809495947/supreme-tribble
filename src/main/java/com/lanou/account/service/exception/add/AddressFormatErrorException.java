package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class AddressFormatErrorException extends AddAccountException {
    @Override
    public String getMessage() {
        return "地址格式错误";
    }
}

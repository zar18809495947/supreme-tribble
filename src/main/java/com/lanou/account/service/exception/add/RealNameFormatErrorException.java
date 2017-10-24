package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class RealNameFormatErrorException extends AddAccountException {
    @Override
    public String getMessage() {
        return "20长度以内的汉字、字母和数字的组合";
    }
}

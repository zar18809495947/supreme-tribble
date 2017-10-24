package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class TelIsEmptyException extends AddAccountException {
    @Override
    public String getMessage() {
        return "电话号为空";
    }
}

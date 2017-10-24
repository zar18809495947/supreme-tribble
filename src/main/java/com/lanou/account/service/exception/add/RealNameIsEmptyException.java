package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class RealNameIsEmptyException extends AddAccountException {
    @Override
    public String getMessage() {
        return "实名为空";
    }
}

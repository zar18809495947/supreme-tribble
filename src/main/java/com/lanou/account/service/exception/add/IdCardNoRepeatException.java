package com.lanou.account.service.exception.add;

/**
 * @author zar on 2017/10/24.
 */
public class IdCardNoRepeatException extends AddAccountException {
    @Override
    public String getMessage() {
        return "身份证号重复";
    }
}

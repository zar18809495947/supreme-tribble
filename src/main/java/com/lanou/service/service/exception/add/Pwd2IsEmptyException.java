package com.lanou.service.service.exception.add;

import com.lanou.account.service.exception.add.AddAccountException;

/**
 * @author zar on 2017/10/24.
 */
public class Pwd2IsEmptyException extends AddServiceException {
    @Override
    public String getMessage() {
        return "再次输入密码为空";
    }
}

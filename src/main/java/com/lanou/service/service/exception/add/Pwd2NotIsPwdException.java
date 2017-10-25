package com.lanou.service.service.exception.add;

import com.lanou.account.service.exception.add.AddAccountException;

/**
 * @author zar on 2017/10/24.
 */
public class Pwd2NotIsPwdException extends AddServiceException {
    @Override
    public String getMessage() {
        return "两次密码匹配不上";
    }
}

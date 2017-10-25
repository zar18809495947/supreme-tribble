package com.lanou.service.service.exception.add;

import com.lanou.account.service.exception.add.AddAccountException;

/**
 * @author zar on 2017/10/24.
 */
public class PwdIsEmptyException extends AddServiceException {
    @Override
    public String getMessage() {
        return "密码为空";
    }
}

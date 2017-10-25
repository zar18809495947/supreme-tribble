package com.lanou.service.service.exception.add;

import com.lanou.account.service.exception.add.AddAccountException;

/**
 * @author zar on 2017/10/24.
 */
public class Pwd2FormatErrorException extends AddServiceException {
    @Override
    public String getMessage() {
        return "30长度以内的字母、数字和下划线的组合";
    }
}

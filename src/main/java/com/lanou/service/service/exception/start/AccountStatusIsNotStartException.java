package com.lanou.service.service.exception.start;

/**
 * @author zar on 2017/10/25.
 */
public class AccountStatusIsNotStartException extends StartServiceException {
    @Override
    public String getMessage() {
        return "账务账号未开通";
    }
}

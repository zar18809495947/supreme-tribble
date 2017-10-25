package com.lanou.service.service.exception.add;

public class AccountNotExistException extends AddServiceException {
    @Override
    public String getMessage() {
        return "账务账号不存在或已删除";
    }
}

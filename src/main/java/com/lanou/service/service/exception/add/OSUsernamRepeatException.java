package com.lanou.service.service.exception.add;

public class OSUsernamRepeatException extends AddServiceException {
    @Override
    public String getMessage() {
        return "os账号重复";
    }
}

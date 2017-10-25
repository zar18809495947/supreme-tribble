package com.lanou.service.service.exception.add;

public class IdcardNotExistException extends AddServiceException {
    @Override
    public String getMessage() {
        return "身份证不存在";
    }
}

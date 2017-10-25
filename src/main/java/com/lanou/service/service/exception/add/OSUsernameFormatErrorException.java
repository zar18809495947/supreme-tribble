package com.lanou.service.service.exception.add;

public class OSUsernameFormatErrorException extends AddServiceException {
    @Override
    public String getMessage() {
        return "8长度以内的字母、数字和下划线的组合";
    }
}

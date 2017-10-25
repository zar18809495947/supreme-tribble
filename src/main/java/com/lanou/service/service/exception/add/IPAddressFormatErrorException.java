package com.lanou.service.service.exception.add;

public class IPAddressFormatErrorException extends AddServiceException {
    @Override
    public String getMessage() {
        return "IP地址格式错误";
    }
}

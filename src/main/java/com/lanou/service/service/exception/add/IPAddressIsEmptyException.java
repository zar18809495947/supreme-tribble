package com.lanou.service.service.exception.add;

public class IPAddressIsEmptyException extends AddServiceException {
    @Override
    public String getMessage() {
        return "IP地址为空";
    }
}

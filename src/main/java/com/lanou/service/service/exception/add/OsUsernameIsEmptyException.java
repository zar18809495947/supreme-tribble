package com.lanou.service.service.exception.add;

public class OsUsernameIsEmptyException extends AddServiceException {
    @Override
    public String getMessage() {
        return "os账号为空";
    }
}

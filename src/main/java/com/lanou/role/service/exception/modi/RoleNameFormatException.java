package com.lanou.role.service.exception.modi;

public class RoleNameFormatException extends ModiRoleException {
    @Override
    public String getMessage() {
        return "为20长度的字母、数字和汉字的组合";
    }
}

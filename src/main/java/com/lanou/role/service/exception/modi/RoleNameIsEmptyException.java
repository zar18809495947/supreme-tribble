package com.lanou.role.service.exception.modi;

public class RoleNameIsEmptyException extends ModiRoleException {
    @Override
    public String getMessage() {
        return "角色名为空";
    }
}

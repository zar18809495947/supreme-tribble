package com.lanou.role.service.exception.modi;

public class RoleNameRepeatException extends ModiRoleException {
    @Override
    public String getMessage() {
        return "角色名已存在";
    }
}

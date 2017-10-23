package com.lanou.cost.service.exception.add;

public class NameFormatErrorException extends AddCostException {
    @Override
    public String getMessage() {
        return "50长度的字母、数字、汉字和下划线的组合";
    }
}

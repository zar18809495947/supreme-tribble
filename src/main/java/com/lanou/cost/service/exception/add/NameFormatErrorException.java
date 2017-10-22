package com.lanou.cost.service.exception.add;

public class NameFormatErrorException extends AddCostException {
    @Override
    public String getMessage() {
        return "名字格式错误";
    }
}

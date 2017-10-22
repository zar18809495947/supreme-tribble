package com.lanou.cost.service.exception.add;

public class NameRepeatException extends AddCostException {
    @Override
    public String getMessage() {
        return "名字重复";
    }
}

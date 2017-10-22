package com.lanou.cost.service.exception.add;

public class NameIsEmptyException extends AddCostException {
    @Override
    public String getMessage() {
        return "名字为空";
    }
}

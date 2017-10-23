package com.lanou.cost.service.exception.add;

/**
 * @author zar on 2017/10/23.
 */
public class BaseDurationIsEmptyException extends AddCostException {
    @Override
    public String getMessage() {
        return "基本类型为空";
    }
}

package com.lanou.cost.service.exception.add;

/**
 * @author zar on 2017/10/23.
 */
public class BaseCostIsEmptyException extends AddCostException {
    @Override
    public String getMessage() {
        return "基本费用为空";
    }
}

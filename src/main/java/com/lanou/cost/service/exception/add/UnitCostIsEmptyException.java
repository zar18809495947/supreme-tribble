package com.lanou.cost.service.exception.add;

/**
 * @author zar on 2017/10/23.
 */
public class UnitCostIsEmptyException extends AddCostException {
    @Override
    public String getMessage() {
        return "单位费用为空";
    }
}

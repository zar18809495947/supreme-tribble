package com.lanou.cost.service.exception.add;

/**
 * @author zar on 2017/10/23.
 */
public class UnitCostFormatErrorException extends AddCostException {
    @Override
    public String getMessage() {
        return "0-99999.99之间的数值";
    }
}

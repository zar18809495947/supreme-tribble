package com.lanou.cost.service.exception.add;

/**
 * @author zar on 2017/10/23.
 */
public class BaseDurationFormatErrorException extends AddCostException {
    @Override
    public String getMessage() {
        return "1-600之间的整数";
    }
}

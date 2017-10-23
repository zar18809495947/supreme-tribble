package com.lanou.cost.service.exception.add;

/**
 * @author zar on 2017/10/23.
 */
public class BaseCostNotMatchException extends AddCostException {
    @Override
    public String getMessage() {
        return "基本费用与资费类型不匹配";
    }
}

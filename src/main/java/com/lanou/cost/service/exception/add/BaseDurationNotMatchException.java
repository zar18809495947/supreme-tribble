package com.lanou.cost.service.exception.add;

/**
 * @author zar on 2017/10/23.
 */
public class BaseDurationNotMatchException extends AddCostException {
    @Override
    public String getMessage() {
        return "基本时长与资费类型不匹配";
    }
}

package com.lanou.cost.service.exception.add;

/**
 * @author zar on 2017/10/23.
 */
public class DescrFormatErrorException extends AddCostException {
    @Override
    public String getMessage() {
        return "100长度的字母、数字、汉字和下划线的组合";
    }
}

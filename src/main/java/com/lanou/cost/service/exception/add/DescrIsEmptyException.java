package com.lanou.cost.service.exception.add;

/**
 * @author zar on 2017/10/23.
 */
public class DescrIsEmptyException extends AddCostException {
    @Override
    public String getMessage() {
        return "资费说明为空";
    }
}

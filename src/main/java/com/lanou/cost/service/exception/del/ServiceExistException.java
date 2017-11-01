package com.lanou.cost.service.exception.del;

/**
 * @author zar on 2017/11/1.
 */
public class ServiceExistException extends CostDeleteException {
    @Override
    public String getMessage() {
        return "业务账号存在,无法删除";
    }
}

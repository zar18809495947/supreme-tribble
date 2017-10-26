package com.lanou.role.bean;

import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
public class Module {
    private Integer moduleId;
    private String moduleName;
    private List<Role> roleList;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}

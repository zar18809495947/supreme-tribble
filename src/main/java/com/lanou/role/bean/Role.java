package com.lanou.role.bean;

import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
public class Role {
    private Integer roleId;
    private String roleName;
    private List<Module> moduleList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", moduleList=" + moduleList +
                '}';
    }
}

package com.lanou.service.bean;

import com.lanou.account.bean.Account;
import com.lanou.cost.bean.Cost;

import java.util.Date;

public class Servicezz {
    private Integer serviceId;

    //    private Integer accountId;
    private Account account;// accountId

    private String unixHost;

    private String osUsername;

    private String loginPasswd;

    private String status;

    private String createDate;

    private String pauseDate;

    private String closeDate;

    //    private Integer costId;
    private Cost cost; // costId

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }


    public String getUnixHost() {
        return unixHost;
    }

    public void setUnixHost(String unixHost) {
        this.unixHost = unixHost == null ? null : unixHost.trim();
    }

    public String getOsUsername() {
        return osUsername;
    }

    public void setOsUsername(String osUsername) {
        this.osUsername = osUsername == null ? null : osUsername.trim();
    }

    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        this.loginPasswd = loginPasswd == null ? null : loginPasswd.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(String pauseDate) {
        this.pauseDate = pauseDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Servicezz{" +
                "serviceId=" + serviceId +
                ", account=" + account +
                ", unixHost='" + unixHost + '\'' +
                ", osUsername='" + osUsername + '\'' +
                ", loginPasswd='" + loginPasswd + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", pauseDate=" + pauseDate +
                ", closeDate=" + closeDate +
                ", cost=" + cost +
                '}';
    }
}
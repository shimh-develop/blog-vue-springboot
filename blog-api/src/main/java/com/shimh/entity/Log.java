package com.shimh.entity;

import com.shimh.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author shimh
 * <p>
 * 2018年4月18日
 */
@Entity
@Table(name = "sys_log")
public class Log extends BaseEntity<Integer> {

    @Column(name = "userid")
    private Long userId;

    @Column(name = "nickname", length = 10)
    private String nickname;

    @Column(name = "module", length = 10)
    private String module;

    @Column(name = "operation", length = 25)
    private String operation;

    @Column(name = "method", length = 100)
    private String method;

    @Column(name = "params")
    private String params;

    @Column(name = "time")
    private Long time;

    @Column(name = "ip", length = 15)
    private String ip;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Log() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

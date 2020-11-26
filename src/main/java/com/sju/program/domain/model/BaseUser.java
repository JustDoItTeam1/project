package com.sju.program.domain.model;

import com.alibaba.excel.annotation.ExcelIgnore;

public class BaseUser {

    /**权限标识**/
    @ExcelIgnore
    private int authenticate;
    @ExcelIgnore
    private Long id;
    @ExcelIgnore
    private String userName;
    @ExcelIgnore
    private String userPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(int authenticate) {
        this.authenticate = authenticate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

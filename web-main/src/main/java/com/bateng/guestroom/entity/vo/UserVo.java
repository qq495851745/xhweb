package com.bateng.guestroom.entity.vo;

/**
 * @author 张伟金
 * @date 2020/5/8-15:38
 */
public class UserVo {
    private int id;
    private String password;
    private String newPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

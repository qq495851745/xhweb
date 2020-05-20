package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer id;

    @Column(name = "username")
    private String username; //用户名

    @Column(name = "upassword")
    private String password; //密码

    @Column(name = "realname")
    private String realName;//真实姓名

    @Column(name="class_name")
    private String className;//班级名字

    @Column(name = "tel")
    private String tel;//手机号码

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;//角色

    @ManyToOne
    @JoinColumn(name="user_level_id")
    private UserLevel userLevel;//所属用户层级

    @Column(name = "delflag")
    private Integer flag;//删除标记

    @Column(name = "delete_role")
    private int deleteRole;

    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name="updatetime")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @Column(name ="des")
    private String des;//描述

    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getDeleteRole() {
        return deleteRole;
    }

    public void setDeleteRole(int deleteRole) {
        this.deleteRole = deleteRole;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}

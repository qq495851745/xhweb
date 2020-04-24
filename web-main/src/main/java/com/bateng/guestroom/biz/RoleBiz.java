package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Role;
import com.bateng.guestroom.entity.User;

import java.util.List;

public interface RoleBiz {

    /**
     * 添加角色
     * */
    public void addRole(Role role);

    /**
     * 返回所有角色 json
     * @return
     */
    public  String findRoleAjax();

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> findRole();

    public PageVo<Role> findRoleByPage(PageVo<Role> pageVo, Role role);

    public List<Role> findRoleByName(Role role);
}

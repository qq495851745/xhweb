package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Role;

public interface RoleRepository {
    public PageVo<Role> findRoleByPage(PageVo<Role> pageVo, Role role);
}

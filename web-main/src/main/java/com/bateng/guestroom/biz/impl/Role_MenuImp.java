package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.Role_menu;
import com.bateng.guestroom.dao.T_Role_menuDao;
import com.bateng.guestroom.entity.Role_Menu;

public class Role_MenuImp implements Role_menu {
    private T_Role_menuDao t_role_menuDao;
    @Override
    public Role_Menu findByR_id(int id) {
        return t_role_menuDao.findByR_id(id);
    }
}

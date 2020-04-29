package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bateng.guestroom.biz.RoleBiz;
import com.bateng.guestroom.dao.RoleDao;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("roleBiz")
public class RoleBizImpl implements RoleBiz {
    @Autowired
    private RoleDao roleDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public String findRoleAjax() {
        List<Role> roles=roleDao.findAll();
        return JSONObject.toJSONString(roles,new SerializeFilter[]{
                new PropertyFilter() {
                    @Override
                    public boolean apply(Object o, String s, Object o1) {
                        return s.equals("id") || s.equals("name");
                    }
                }
        }, SerializerFeature.DisableCircularReferenceDetect);

    }

    @Override
    public PageVo<Role> findRoleByPage(PageVo<Role> pageVo, Role role) {
        return roleDao.findRoleByPage(pageVo,role);
    }

    @Override
    public List<Role> findRoleByName(Role role) {
        return roleDao.findAllByName(role.getName());
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        roleDao.save(role);
    }



    @Override
    public Role getRoleById(int id) {
        return roleDao.getOne(id);
    }


    @Override
    public List<Role> findRole() {
        return roleDao.findAllByFlag(1);
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}

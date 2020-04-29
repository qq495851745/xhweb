package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.RoleBiz;
import com.bateng.guestroom.entity.Role;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/ajax/role")
public class RoleAjaxController {
    @Autowired
    private RoleBiz roleBiz;

    @RequestMapping(value = "findRole" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findRole(HttpSession session){
        Role role = (Role) session.getAttribute("role");
        return roleBiz.findRoleAjax();
        //return menuBiz.findMenusAjax();
    }
    public RoleBiz getRoleBiz() {
        return roleBiz;
    }

    public void setRoleBiz(RoleBiz roleBiz) {
        this.roleBiz = roleBiz;
    }
}

package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.RoleBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Role;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/guestroom")
public class RoleController {

    @Autowired
    private RoleBiz roleBiz;

    @RequestMapping(value = "/role/ajax",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAllAjax(){
        return roleBiz.findRoleAjax();
    }

    @RequestMapping(value = "/role",method = {RequestMethod.GET})
    public String findAll(Model model){
        List<Role> roles=roleBiz.findRole();
        model.addAttribute("roles",roles);
        return  "user/user_add_lookup_role";
    }

    //restful  命名规则
    @RequestMapping(value = "/role/index",method = RequestMethod.GET)
    public String index(PageVo<Role> pageVo, Role role, Model model){
       pageVo=roleBiz.findRoleByPage(pageVo,role);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("role", role);
        return "role/role_index";
    }
    //跳转修改页面
    @RequestMapping(value = "/role/{id}",method = {RequestMethod.GET})
    public String toEdit(@PathVariable("id") int id, Model model) {
        Role role = roleBiz.getRoleById(id);
        model.addAttribute("role",role);
        return "role/role_edit";
    }

    //做修改
    @RequestMapping(value = "/role",method = RequestMethod.PUT,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doEdit(Role role){
        roleBiz.updateRole(role);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message", "更新完成!");
        jsonObject.put("navTabId", "w_41");
        jsonObject.put("callbackType","closeCurrent");
        return  jsonObject.toJSONString();
    }

    @RequestMapping(value = "/role/")

    public RoleBiz getRoleBiz() {
        return roleBiz;
    }

    public void setRoleBiz(RoleBiz roleBiz) {
        this.roleBiz = roleBiz;
    }
}

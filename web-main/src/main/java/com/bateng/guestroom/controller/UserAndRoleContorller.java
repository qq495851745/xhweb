package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.RoleBiz;
import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/guestroom")
public class UserAndRoleContorller {
    @Autowired
    private UserBiz userBiz;

    //跳转首页
    @RequestMapping(value = "/user_role/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(PageVo<User> pageVo, User user, Model model) {
        pageVo = userBiz.findUserByPage(pageVo, user);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("user", user);
        return "user_role/user_role_index";
    }

    @RequestMapping(value = "/user_role/{id}",method = {RequestMethod.GET})
    public String toEdit(@PathVariable("id") int id, Model model) {
        User user = userBiz.getUserById(id);
        model.addAttribute("user",user);
        return "user_role/user_role_select";
    }

    @RequestMapping(value = "/user_role",  method = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String update(User user) {
        userBiz.updateUser(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message", "选择角色成功!");
        jsonObject.put("navTabId", "w_42");
        jsonObject.put("callbackType","closeCurrent");
        return  jsonObject.toJSONString();
    }

    @RequestMapping(value = "/user_role/selectRoleName",method = {RequestMethod.GET})
    public String selectRoleName() {
        return "user_role/role_select";
    }

}

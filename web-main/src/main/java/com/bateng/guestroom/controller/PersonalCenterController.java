package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.entity.User;
import com.bateng.guestroom.entity.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 张伟金
 * @date 2020/5/8-8:47
 */
@Controller
@RequestMapping("personalCenter")
public class PersonalCenterController extends BaseController {

    @Autowired
    private UserBiz userBiz;

    @RequestMapping("/changePasswordPage")
    public String toChangePasswordPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "personal/changePassword";
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.PUT,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doChangePassword(UserVo userVo){
        JSONObject jsonObject = new JSONObject();
        User user = userBiz.getUserById(userVo.getId());
        if(userVo.getPassword().equals(user.getPassword())){
            user.setPassword(userVo.getNewPassword());
            userBiz.updateUser(user);
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("message","修改成功");
            jsonObject.put("callbackType", "closeCurrent");
            jsonObject.put("navTabId", "w_51");
        }else{
            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
            jsonObject.put("message","原密码错误，修改失败!");
        }
        return jsonObject.toJSONString();
    }
}

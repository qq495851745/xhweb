package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author 张伟金
 * @date 2020/5/6-18:19
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/toRegister",method = RequestMethod.GET)
    public String toRegister(){
        return "registerNew";
    }

    @RequestMapping(value = "/doRegister",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String doRegister(User user, HttpServletRequest request, Model model){
        List<User> users = userBiz.findUserByName(user);
        if(users.size() > 0){
            model.addAttribute("userMsg","用户名已存在");
            return "registerNew";
        }
        String pw1 = request.getParameter("password");
        String pw2 = request.getParameter("password2");
        if(!pw1.equals(pw2)){
            model.addAttribute("passwordMsg","密码不一致");
            return "registerNew";
        }
        List<User> userList = userBiz.findUserByEmail(user);
        if(userList.size() > 0){
            model.addAttribute("emailMsg","该邮箱已被注册");
            return "registerNew";
        }
        user.setCreateDate(new Date());
        user.setFlag(0);
        user.setDeleteRole(0);
        userBiz.addUser(user);
        addurl(model);
        model.addAttribute("msg","提交成功,待管理员审核");
        return "registerMsg";
    }

    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String index(Model model){
        addurl(model);
        return "login";
    }
}

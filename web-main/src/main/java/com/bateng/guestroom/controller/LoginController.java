package com.bateng.guestroom.controller;


import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.model.WebConfig;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.ref.ReferenceQueue;
import java.nio.channels.SeekableByteChannel;
import java.util.List;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserBiz userBiz;

    //跳转登录页面
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model){
        addurl(model);
        return "login";
    }

    //跳转登录页面
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(Model model){
        addurl(model);
        return "register";
    }

    @RequestMapping(value = "ruser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ruser(User user,Model model){
        String message="";
        List<User> list=userBiz.findUserByName(user);
        if(list.size()!=0){
            message="用户名已经注册，不可以使用";
            model.addAttribute("user",user);
            addurl(model);
            return "{\"flag\":\"false\",\"message\":\"用户名已经注册，不可以使用\"}";
        }
        user.setFlag(0);
        userBiz.addUser(user);
        return "{\"flag\":\"true\",\"message\":\"用户名注册成功，请等待审核通过后再登录系统，请勿重复注册!\"}";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(User user, Model model, HttpServletRequest request){
        HttpSession session= request.getSession();
//        session.setMaxInactiveInterval(60*10);
        addurl(model);
        user=userBiz.checkUser(user);
        if(user==null){
            session.setAttribute("message","用户名，或密码输入不正确");
            return "login";
        }
        else {
              if(user.getFlag()==0){
                  session.setAttribute("message","注册的用户还没有审核，请等待！");
                  return "login";
              }
            if(user.getFlag()==2){
                session.setAttribute("message","审核不通过!"+user.getDes());
                return "login";
            }
            session.setAttribute("user",user);
            return "redirect:index";
        }
    }

    @RequestMapping(value = "index")
    public String toIndex(Model model){
        addurl(model);
        return  "index";
    }






    //退出操作
    @RequestMapping(value = "loginout",method = RequestMethod.GET)
    public String loginout(Model model,HttpSession session){
        addurl(model);
        session.invalidate();
        return "login";
    }


    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }
}

package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.GradeBiz;
import com.bateng.guestroom.entity.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/ajax/grade")
public class GredeAjaxController {
    @Autowired
    private GradeBiz gradeBiz;

    @RequestMapping(value = "findGrade" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findGrade(HttpSession session){
        Grade grade = (Grade) session.getAttribute("grade");
        return  gradeBiz.findGradeAjax();
    }
}

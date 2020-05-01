package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.SubjectBiz;
import com.bateng.guestroom.entity.Role;
import com.bateng.guestroom.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/ajax/subject")
public class SubjectAjaxController {
    @Autowired
    private SubjectBiz subjectBiz;

    @RequestMapping(value = "findSubject" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findSubject(HttpSession session){
        Subject subject = (Subject) session.getAttribute("subject");
        return subjectBiz.findSubjectAjax();
    }
}

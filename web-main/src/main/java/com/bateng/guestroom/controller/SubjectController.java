package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.SubjectBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.entity.*;
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
public class SubjectController {
    @Autowired
    private SubjectBiz subjectBiz;

    //跳转首页
    @RequestMapping(value = "/subject/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Subject subject, Model model) {
        model.addAttribute("subject",subject);
        return "book/book_subject";
    }

    //跳转添加页面
    @RequestMapping(value = "/subject/toAdd",method = {RequestMethod.GET})
    public String toAdd(){
        return "book/subject_add";
    }

    //添加
    //做添加操作
    @RequestMapping(value = "/subject", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(Subject subject,Model model) {
        JSONObject jsonObject = new JSONObject();
        //验证类别名是否存在
        List<Subject> subjects = subjectBiz.findAllByName(subject);
        if (subjects.size() > 0) {//这个类别已经存在
            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
            jsonObject.put("message", "当前书籍类别已经存在，不能使用");
        } else {
            subjectBiz.addSubject(subject);
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("callbackType", "closeCurrent");//关闭当前标签页
            jsonObject.put("navTabId", "w_34");
            jsonObject.put("message", "类别添加成功");
        }
        return jsonObject.toJSONString();
    }

    /**
     * 跳转每个类别的子类
     * */
    @RequestMapping(value = "/subject/subjectIndex/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public String subject_index(@PathVariable("id") int id,PageVo<Subject> pageVo,Subject subject, Model model){
        pageVo = subjectBiz.findSubjectByPage(pageVo, subject);
        List<Subject> subjects = subjectBiz.findAllByPid(id);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("subject",subject);
        model.addAttribute("subjects",subjects);
        model.addAttribute("pid",id);
        return "book/subject_information";
    }

    /**
     * 删除类别
     * */
    @RequestMapping(value = "/subject/delete/{id}",method = {RequestMethod.DELETE,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteSubject(@PathVariable("id") int id,Subject subject){
        subject = subjectBiz.getSubjectById(id);
        List<Subject> subjects =  subjectBiz.findAllByPid(subject.getId());
        JSONObject jsonObject = new JSONObject();
        if(subjects.size()==0){
            subjectBiz.deleteSubjectById(id);
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("message", "删除成功!");
            jsonObject.put("navTabId", "w_34");
        }
       else if(subjects.size()!=0){
            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
            jsonObject.put("message", "该数据有子级,不能删除!");
        }
        return jsonObject.toJSONString();
    }

    //跳转修改页面
    @RequestMapping(value = "/subject/update/{id}",method = RequestMethod.GET)
    public String modify(@PathVariable("id") int id,Subject subject,Model model){
        subject =  subjectBiz.getSubjectById(id);
        model.addAttribute("subject",subject);
        return "book/subject_edit";
    }

    //修改
    @RequestMapping(value = "/subject/update",method = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateSubject(Subject subject){
        subjectBiz.updateSubject(subject);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("navTabId", "w_34");
        jsonObject.put("callbackType","closeCurrent");
        jsonObject.put("message", "修改完成!");
        return  jsonObject.toJSONString();
    }

}

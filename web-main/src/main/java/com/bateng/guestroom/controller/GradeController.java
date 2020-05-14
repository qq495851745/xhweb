package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.GradeBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.entity.Grade;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/guestroom")
public class GradeController {
    @Autowired
    private GradeBiz gradeBiz;

    //跳转首页
    @RequestMapping(value = "/grade/index" ,method = {RequestMethod.GET, RequestMethod.POST})
    public String grade_index(Grade grade,Model model){
        model.addAttribute("Grade",grade);
        return "grade/grade_index";
    }

    /**
     * 跳转每个类别的子类
     * */
    @RequestMapping(value = "/grade/gradeIndex/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public String grade_index(@PathVariable("id") int id, PageVo<Grade> pageVo, Grade grade, Model model){
        pageVo = gradeBiz.findGradeByPage(pageVo,grade);
        List<Grade> grades = gradeBiz.findAllByPid(id);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("grade",grade);
//        model.addAttribute("grade",grades);
        model.addAttribute("pid",id);
        return "grade/grade_information";
    }

    //跳转添加页面
    @RequestMapping(value = "/grade/toAdd",method = {RequestMethod.GET})
    public String toAdd(){
        return "grade/grade_add";
    }

    //添加
    //做添加操作
    @RequestMapping(value = "/grade", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(Grade grade,Model model) {
        JSONObject jsonObject = new JSONObject();
        //验证类别名是否存在
        List<Grade> grades = gradeBiz.findAllByName(grade);
        if (grades.size() > 0) {//这个类别已经存在
            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
            jsonObject.put("message", "当前班级已存在，不能使用");
        } else {
            gradeBiz.addGrade(grade);
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("callbackType", "closeCurrent");//关闭当前标签页
            jsonObject.put("navTabId", "w_45");
            jsonObject.put("message", "班级添加成功");
        }
        return jsonObject.toJSONString();
    }


    /**
     * 删除班级
     * */
    @RequestMapping(value = "/grade/delete/{id}",method = {RequestMethod.DELETE,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteSubject(@PathVariable("id") int id,Grade grade){
        grade = gradeBiz.getGradeById(id);
        List<Grade> grades =  gradeBiz.findAllByPid(grade.getId());
        JSONObject jsonObject = new JSONObject();
        if(grades.size()==0){
            gradeBiz.deleteGradeById(id);
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("message", "删除成功!");
            jsonObject.put("navTabId", "w_45");
        }
        else if(grades.size()!=0){
            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
            jsonObject.put("message", "该数据有子级,不能删除!");
        }
        return jsonObject.toJSONString();
    }

    //跳转修改页面
    @RequestMapping(value = "/grade/update/{id}",method = RequestMethod.GET)
    public String modify(@PathVariable("id") int id,Grade grade,Model model){
        grade =  gradeBiz.getGradeById(id);
        model.addAttribute("grade",grade);
        return "grade/grade_updategrade";
    }

    //修改
    @RequestMapping(value = "/grade/update",method = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateSubject(Grade grade){
        gradeBiz.updateGrade(grade);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("navTabId", "w_45");
        jsonObject.put("callbackType","closeCurrent");
        jsonObject.put("message", "修改完成!");
        return  jsonObject.toJSONString();
    }

}

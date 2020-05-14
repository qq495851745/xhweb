package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import com.bateng.guestroom.biz.GradeBiz;
import com.bateng.guestroom.dao.GradeDao;
import com.bateng.guestroom.entity.Grade;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gradeBiz")
public class GradeBizImpl implements GradeBiz {
    @Autowired
    private GradeDao gradeDao;


    @Override
    public List<Grade> findGrade() {
        return gradeDao.findAll();
    }

    @Override
    public PageVo<Grade> findGradeByPage(PageVo<Grade> pageVo, Grade grade) {
        return gradeDao.findGradeByPage(pageVo,grade);
    }

    @Override
    public String findGradeAjax() {
        List<Grade> grades = gradeDao.findAll();
        return JSONObject.toJSONString(grades,new SerializeFilter[]{
                new PropertyFilter() {
                    @Override
                    public boolean apply(Object o, String s, Object o1) {
                        return s.equals("id") || s.equals("name")||s.equals("grade");
                    }
                },
                new NameFilter() {
                    @Override
                    public String process(Object o, String s, Object o1) {
                        if (s.equals("id"))
                            return "id";
                        else if (s.equals("grade"))
                            return "pId";
                        else if (s.equals("name"))
                            return "name";
                        else
                            return s;
                    }
                }, new ValueFilter() {
            @Override
            public Object process(Object o, String s, Object o1) {
                if (s.equals("pId"))
                    if (o1 != null)
                        return ((Grade) o1).getId();
                    else
                        return 0;
                return o1;
            }
        }
        }, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public List<Grade> findAllByPid(int pid) {
        return gradeDao.findAllByPid(pid);
    }

    @Override
    public void addGrade(Grade grade) {
        gradeDao.save(grade);
    }

    @Override
    public Grade getGradeById(int id) {
        return gradeDao.findGradeById(id);
    }

    @Override
    public List<Grade> findAllByName(Grade grade) {
        return gradeDao.findAllByName(grade.getName());
    }


    @Override
    public void deleteGradeById(int id) {
        gradeDao.deleteById(id);
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeDao.updateGrade(grade.getName(),grade.getZesc(),grade.getId());
    }
}

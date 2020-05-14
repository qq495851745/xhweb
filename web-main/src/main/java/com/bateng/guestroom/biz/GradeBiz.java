package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Grade;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;

import java.util.List;

public interface GradeBiz {
    public List<Grade> findGrade();

    public PageVo<Grade> findGradeByPage(PageVo<Grade> pageVo, Grade grade);
    public String findGradeAjax();

    public List<Grade> findAllByPid(int pid);

    public void addGrade(Grade grade);

    public Grade getGradeById(int id);

    public List<Grade> findAllByName(Grade grade);

    public void deleteGradeById(int id);

    public void updateGrade(Grade grade);

}

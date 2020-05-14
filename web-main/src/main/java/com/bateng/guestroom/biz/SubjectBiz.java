package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.*;

import java.util.List;

public interface SubjectBiz {
    public List<Subject> findSubject();

    public PageVo<Subject> findSubjectByPage(PageVo<Subject> pageVo, Subject subject);
    public String findSubjectAjax();

    public void addSubject(Subject subject);

    public List<Subject> findAllByName(Subject subject);

    public Subject getSubjectById(int id);


    public List<Subject> findAllByPid(int pid);

    /**
     * 做删除操作
     * @param id
     */
    public void deleteSubjectById(int id);

    public void updateSubject(Subject subject);


}

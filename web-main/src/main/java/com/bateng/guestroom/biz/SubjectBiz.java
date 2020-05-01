package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Role;
import com.bateng.guestroom.entity.Subject;

import java.util.List;

public interface SubjectBiz {
    public List<Subject> findSubject();
    public String findSubjectAjax();
    public void addSubject(Subject subject);

    public List<Subject> findAllByName(Subject subject);
}

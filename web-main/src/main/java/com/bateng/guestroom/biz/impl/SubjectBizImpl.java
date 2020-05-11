package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import com.bateng.guestroom.biz.SubjectBiz;
import com.bateng.guestroom.dao.SubjectDao;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.Menu;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("subjectBiz")
public class SubjectBizImpl implements SubjectBiz {
    @Autowired
    private SubjectDao subjectDao;

    public List<Subject> findAllByPid(int pid){
        return subjectDao.findAllByPid(pid);
    }

    @Override
    public void deleteSubjectById(int id) {
        subjectDao.deleteById(id);
    }

    @Override
    public void updateSubject(Subject subject) {
        subjectDao.updateSubject(subject.getName(),subject.getDesc(),subject.getId());
    }


    @Override
    public List<Book> findBookById(int id) {
        return subjectDao.findBookById(id);
    }

    @Override
    public List<Subject> findSubject() {
        return subjectDao.findAllByFlag(1);
    }

    @Override
    public PageVo<Subject> findSubjectByPage(PageVo<Subject> pageVo, Subject subject) {
        return subjectDao.findSubjectByPage(pageVo,subject);
    }

    @Override
    public String findSubjectAjax() {
        List<Subject> subjects=subjectDao.findAll();
        return JSONObject.toJSONString(subjects,new SerializeFilter[]{
                new PropertyFilter() {
                    @Override
                    public boolean apply(Object o, String s, Object o1) {
                        return s.equals("id") || s.equals("name")||s.equals("subject");
                    }
                },
                new NameFilter() {
                    @Override
                    public String process(Object o, String s, Object o1) {
                        if (s.equals("id"))
                            return "id";
                        else if (s.equals("subject"))
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
                        return ((Subject) o1).getId();
                    else
                        return 0;
                return o1;
            }
        }
        }, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public void addSubject(Subject subject) {
        subjectDao.save(subject);
    }

    @Override
    public List<Subject> findAllByName(Subject subject) {
        return subjectDao.findAllByName(subject.getName());
    }

    @Override
    public Subject getSubjectById(int id) {
        return subjectDao.findSubjectById(id);
    }
}

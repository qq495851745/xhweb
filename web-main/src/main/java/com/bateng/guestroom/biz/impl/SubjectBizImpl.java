package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bateng.guestroom.biz.SubjectBiz;
import com.bateng.guestroom.dao.SubjectDao;
import com.bateng.guestroom.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("subjectBiz")
public class SubjectBizImpl implements SubjectBiz {
    @Autowired
    private SubjectDao subjectDao;
    @Override
    public List<Subject> findSubject() {
        return subjectDao.findAll();
    }

    @Override
    public String findSubjectAjax() {
        List<Subject> subjects=subjectDao.findAll();
        return JSONObject.toJSONString(subjects,new SerializeFilter[]{
                new PropertyFilter() {
                    @Override
                    public boolean apply(Object o, String s, Object o1) {
                        return s.equals("id") || s.equals("name");
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
}

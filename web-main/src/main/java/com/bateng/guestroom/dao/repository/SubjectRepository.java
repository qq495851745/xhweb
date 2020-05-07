package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;

public interface SubjectRepository {
    public PageVo<Subject> findSubjectByPage(PageVo<Subject> pageVo, Subject subject);
}

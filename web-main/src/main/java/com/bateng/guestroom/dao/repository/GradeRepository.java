package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.Grade;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;

public interface GradeRepository {
    public PageVo<Grade> findGradeByPage(PageVo<Grade> pageVo, Grade grade);
}

package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Role;
import com.bateng.guestroom.entity.Subject;

public interface SubjectRepository {
    public PageVo<Subject> findRoleByPage(PageVo<Subject> pageVo, Subject subject);
}

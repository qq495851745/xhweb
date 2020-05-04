package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.SubjectRepository;
import com.bateng.guestroom.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectDao extends JpaRepository<Subject,Integer>, SubjectRepository {
    public List<Subject> findAllByName(String name);
}

package com.bateng.guestroom.dao;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.bateng.guestroom.dao.repository.SubjectRepository;
import com.bateng.guestroom.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubjectDao extends JpaRepository<Subject,Integer>, SubjectRepository {
    public List<Subject> findAllByName(String name);

    public List<Subject> findAllByFlag(int flag);

    public Subject findSubjectById(int id);

    @Query("select s from Subject s where s.subject.id=:pid")
    @Modifying
    public List<Subject> findAllByPid(int pid);

    @Transactional
    @Query("delete  from Subject s where s.id=:id")
    @Modifying
    public void deleteById(Integer id);

    @Transactional
    @Query("update Subject s set s.name=:name,s.desc=:desc where s.id= :id")
    @Modifying
    public void updateSubject(String name,String desc,int id);

    @Query("select b from Book b where b.subject.id=:id")
    @Modifying
    public List<Book> findBookById(int id);
}

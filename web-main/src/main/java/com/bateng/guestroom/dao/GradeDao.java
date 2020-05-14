package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.GradeRepository;
import com.bateng.guestroom.entity.Grade;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GradeDao extends JpaRepository<Grade,Integer>, GradeRepository {
    public List<Grade> findAllByName(String name);

    public List<Grade> findAllByFlag(int flag);

    public Grade findGradeById(int id);

    @Query("select g from Grade g where g.grade.id=:pid")
    @Modifying
    public List<Grade> findAllByPid(int pid);

    @Transactional
    @Query("delete  from Grade g where g.id=:id")
    @Modifying
    public void deleteById(Integer id);

    @Transactional
    @Query("update Grade g set g.name=:name,g.zesc=:zesc where g.id= :id")
    @Modifying
    public void updateGrade(String name,String zesc,int id);
}

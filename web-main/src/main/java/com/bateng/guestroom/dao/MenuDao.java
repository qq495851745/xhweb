package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface MenuDao extends JpaRepository<Menu,Integer> {

    @Query("from Menu u where u.flag=1 order by u.orderby1 asc")
    public List<Menu> findAllByFlag1();


    public String findMenuById(Integer id);



}

package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.RoleRepository;
import com.bateng.guestroom.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer>, RoleRepository {

    public List<Role> findAllByFlag(int flag);

    public List<Role> findAllByName(String name);

    public int findRoleIdByName(String name);

}

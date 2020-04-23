package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.Role_Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface T_Role_menuDao extends JpaRepository<Role_Menu,Integer> {
    @Query(value = "from t_role_menu t where t.role_id=:id",nativeQuery = true)
    public Role_Menu findByR_id(@Param("id") int id);
}

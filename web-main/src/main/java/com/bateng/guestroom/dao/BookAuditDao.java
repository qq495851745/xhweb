package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.BookAuditRepository;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookAuditDao extends JpaRepository<Book,Integer>, BookAuditRepository {
    public Book findBookById(int id);

    @Query("update Book b set b.rea=:rea,b.flag=:flag  where b.id= :id")
    @Modifying
    public void updateBook(String rea, int flag, int id);
}

package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.BookUploadRepository;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张伟金
 * @date 2020/4/30-18:11
 */
@Repository
public interface BookUploadDao extends JpaRepository<Book,Integer>, BookUploadRepository {
    public List<Book> findAllByName(String name);

    @Query("delete from  Book b where b.name=:name")
    public void deleteBookByName(String name);
}

package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.BookUploadRepository;
import com.bateng.guestroom.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 张伟金
 * @date 2020/4/30-18:11
 */
@Repository
public interface BookUploadDao extends JpaRepository<Book,Integer>, BookUploadRepository {

}

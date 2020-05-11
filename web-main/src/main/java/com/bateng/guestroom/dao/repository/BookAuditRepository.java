package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;

public interface BookAuditRepository {
    public PageVo<Book> findBookAuditByPage(PageVo<Book> pageVo, Book book);
}

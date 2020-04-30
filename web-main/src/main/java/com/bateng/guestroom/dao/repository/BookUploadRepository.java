package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;

/**
 * @author 张伟金
 * @date 2020/4/30-18:14
 */
public interface BookUploadRepository {
    public PageVo<Book> findBookByPage(PageVo<Book> pageVo, Book book);
}

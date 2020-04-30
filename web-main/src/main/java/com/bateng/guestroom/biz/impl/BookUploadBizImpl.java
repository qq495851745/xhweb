package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.BookUploadBiz;
import com.bateng.guestroom.dao.BookUploadDao;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张伟金
 * @date 2020/4/30-18:10
 */
@Service("bookUploadBiz")
public class BookUploadBizImpl implements BookUploadBiz {

    @Autowired
    private BookUploadDao bookUploadDao;

    @Override
    public PageVo<Book> findBookByPage(PageVo<Book> pageVo, Book book) {
        return bookUploadDao.findBookByPage(pageVo,book);
    }
}

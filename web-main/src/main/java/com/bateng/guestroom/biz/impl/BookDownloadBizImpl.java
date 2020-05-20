package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.BookDownloadBiz;
import com.bateng.guestroom.dao.BookDownloadDao;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.BookDownload;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 张伟金
 * @date 2020/5/14-8:38
 */
@Service("bookDownloadBiz")
public class BookDownloadBizImpl implements BookDownloadBiz {

    @Autowired
    private BookDownloadDao bookDownloadDao;


    @Override
    public void addBookDownloadInfo(Book book,User user) {
        BookDownload bookDownload = new BookDownload();
        bookDownload.setBook(book);
        bookDownload.setSubject(book.getSubject());
        bookDownload.setUser(user);
        bookDownload.setDownloadDate(new Date());
        bookDownloadDao.save(bookDownload);
    }
}

package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.BookUploadBiz;
import com.bateng.guestroom.dao.BookUploadDao;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Book> findBookByName(Book book) {
        return bookUploadDao.findAllByName(book.getName());
    }

    @Override
    public void uploadBook(Book book) {
        int i = 1/0;
        bookUploadDao.save(book);
    }

    @Override
    public void deleteBookById(int bid) {
        bookUploadDao.deleteById(bid);
    }

    @Override
    public Book findBookById(int bid) {
        return bookUploadDao.getOne(bid);
    }

    @Override
    public void updateBook(Book book) {
        bookUploadDao.save(book);
    }

    @Override
    public PageVo<Book> findRecycleBookByPage(PageVo<Book> pageVo, Book book) {
        return bookUploadDao.findRecycleBookByPage(pageVo,book);
    }

    @Override
    public List<Book> findAll() {
        return bookUploadDao.findAll();
    }


}

package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bateng.guestroom.biz.BookUploadBiz;
import com.bateng.guestroom.dao.BookUploadDao;
import com.bateng.guestroom.dao.SubjectDao;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        bookUploadDao.save(book);
    }

}

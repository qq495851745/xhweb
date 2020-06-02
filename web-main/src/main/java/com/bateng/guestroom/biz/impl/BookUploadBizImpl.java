package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.BookUploadBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.model.WebConfig;
import com.bateng.guestroom.dao.BookUploadDao;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.vo.UploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author 张伟金
 * @date 2020/4/30-18:10
 */
@Service("bookUploadBiz")
public class BookUploadBizImpl extends BaseController implements BookUploadBiz{

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
    public void uploadBook(Book book, UploadVo uploadVo) throws IOException{

        int i = 1/0;

        String uuid = UUID.randomUUID().toString();
        String fileName = uploadVo.getFile().getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = getWebConfig().getBookPath()+"all/";
        File dest = new File(filePath+uuid+suffixName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        uploadVo.getFile().transferTo(dest);
        book.setPath("all/"+dest.getName());
        book.setFileName(fileName);
        book.setSuffixName(suffixName);

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
    public void deleteBookByName(Book book) {
        bookUploadDao.deleteBookByName(book.getName());
    }

    @Override
    public List<Book> findAll() {
        return bookUploadDao.findAll();
    }


}

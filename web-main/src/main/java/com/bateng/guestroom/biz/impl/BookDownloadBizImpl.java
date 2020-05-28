package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.BookDownloadBiz;
import com.bateng.guestroom.dao.BookDownloadDao;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.BookDownload;
import com.bateng.guestroom.entity.User;
import com.bateng.guestroom.entity.vo.DownLoadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    @Override
    public String getDownloadNum01() {
        List<Object> list=bookDownloadDao.getBookDownloadNum01();
        return JSONObject.toJSONString(list);
    }

    @Override
    public String getDownloadByDownloadDate(Date start, Date end) {
        List<Object> list=bookDownloadDao.getBookDownloadByDownloadDate(start,end);
        return JSONObject.toJSONString(list);
    }

}

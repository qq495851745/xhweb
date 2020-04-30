package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import org.springframework.stereotype.Service;

/**
 * @author 张伟金
 * @date 2020/4/30-18:05
 */
public interface BookUploadBiz {
    public PageVo<Book> findBookByPage(PageVo<Book> pageVo, Book book);
}

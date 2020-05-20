package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.User;

/**
 * @author 张伟金
 * @date 2020/5/14-8:23
 */
public interface BookDownloadBiz {
    public void addBookDownloadInfo(Book book,User user);
}

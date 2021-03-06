package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.BookDownload;
import com.bateng.guestroom.entity.User;
import com.bateng.guestroom.entity.vo.DownLoadVo;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author 张伟金
 * @date 2020/5/14-8:23
 */
public interface BookDownloadBiz {
    public void addBookDownloadInfo(Book book,User user);

    /**
     * 获取每本书的下载量的集合
     * @return  json格式的字符串
     */
    public String getDownloadNum01();

    public String getDownloadByDownloadDate(Date start, Date end);
}

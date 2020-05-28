package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.BookDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface BookDownloadDao extends JpaRepository<BookDownload,Integer> {

    //@Query(value = "select tk.bname,count(*) from t_book_download tb ,t_book tk where tb.book_id=tk.bid group by tk.bname",nativeQuery = true)
    @Query(value = "select tk.bname,count(tb.book_id) from t_book tk left outer join t_book_download tb on(tk.bid = tb.book_id) group by tk.bname",nativeQuery = true)
    public List<Object> getBookDownloadNum01();

    @Query(value = "SELECT tk.bname,COUNT(tb.book_id) AS c FROM t_book tk LEFT OUTER JOIN t_book_download tb\n" +
            "ON(tk.bid = tb.book_id AND tb.download_date>=:start AND tb.download_date<=:end1 )\n" +
            "GROUP BY tk.bname ORDER BY c DESC LIMIT 0,5;",nativeQuery = true)
    public List<Object> getBookDownloadByDownloadDate(Date start, Date end1);
}

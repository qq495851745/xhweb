package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.BookDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张伟金
 * @date 2020/5/14-8:41
 */
@Repository
public interface BookDownloadDao extends JpaRepository<BookDownload,Integer> {

    @Query(value = "select tk.bname,count(*) from t_book_download tb ,t_book tk where tb.book_id=tk.bid group by tk.bname",nativeQuery = true)
    public List<Object> getBookDownloadNum01();

}

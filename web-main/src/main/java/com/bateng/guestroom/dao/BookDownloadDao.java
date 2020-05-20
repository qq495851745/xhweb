package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.BookDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 张伟金
 * @date 2020/5/14-8:41
 */
@Repository
public interface BookDownloadDao extends JpaRepository<BookDownload,Integer> {

}

package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.BookCommentRepository;
import com.bateng.guestroom.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCommentDao extends JpaRepository<Comment,Integer>, BookCommentRepository {
    public List<Comment> findAllByCommentContent(String commentContent);

    public Comment findCommentById(int id);

    @Query("update Comment c set c.rea=:rea,c.flag=:flag  where c.id= :id")
    @Modifying
    public void updateComment(String rea, int flag, int id);

    @Query("from Comment c  where c.book.id=:id")
    @Modifying
    public List<Comment> findAllByBookId(int id);

    @Query("from Comment c  where c.book.id=:id and c.commentContent=:con")
    @Modifying
    public List<Comment> findCommentByCon(int id,String con);
}

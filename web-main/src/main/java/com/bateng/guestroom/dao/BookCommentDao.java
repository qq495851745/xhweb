package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.BookCommentRepository;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.Comment;
import com.bateng.guestroom.entity.Subject;
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
    public List<Comment> findAllByBookId(int id);

    @Query("from Comment c  where c.book.id=:id and c.commentContent=:con")
    public List<Comment> findCommentByCon(int id,String con);

    @Query("from Subject s  where s.id=:bookId ")
    public Subject findSubjectBySubjectId(int bookId);
    @Query("from Subject s  where s.subject.id=:pid ")
    public Subject findSubjectByPid(int pid);

    @Query(value = "select tk.bname,count(tc.book_id) from t_book tk left outer join t_comment tc on(tk.bid = tc.book_id and tc.delflag=1) group by tk.bname",nativeQuery = true)
    public List<Object> getBookCommentNum();
}

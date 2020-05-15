package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.Comment;
import com.bateng.guestroom.entity.PageVo;

public interface BookCommentRepository {
    public PageVo<Comment> findCommentAuditByPage(PageVo<Comment> pageVo, Comment comment);
    public PageVo<Comment> findLookCommentAuditByPage(PageVo<Comment> pageVo, Comment comment);
}

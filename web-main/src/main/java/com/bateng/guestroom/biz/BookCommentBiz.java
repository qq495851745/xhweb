package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.Comment;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;

import java.util.List;

public interface BookCommentBiz {
    /**
     * 添加评论
     * @param comment 添加的对象
     */
    public void addComment(Comment comment);

    /**
     * 分页查询Comment
     * @param pageVo 分页
     * @param comment 评论
     * @return 分页对象
     */
    public PageVo<Comment> findCommentAuditByPage(PageVo<Comment> pageVo, Comment comment);

    /**
     * 通过评论内容查找所有comment
     * @param comment
     * @return 集合对象
     */
    public List<Comment> findAllByCommentContent(Comment comment);

    /**
     * 通过id来查找Comment对象
     * @param id comment.id
     * @return Comment对象
     */
    public Comment getCommentById(int id);

    /**
     * 修改Comment的flag和rea
     * @param comment 评论对象
     */
    public void updateCommentAudit(Comment comment);

    /**
     * 通过book.id来查询所有的Comment对象
     * @param id book.id
     * @return 集合对象
     */
    public List<Comment> findAllByBookId(int id);

    public String findBookAjax();

    /**
     * 分页查询lookComment
     * @param pageVo 分页
     * @param comment 评论
     * @return 分页对象
     */
    public PageVo<Comment> findLookCommentAuditByPage(PageVo<Comment> pageVo, Comment comment);

    /**
     * 通过book.id 和 评论内容来查找Comment
     * @param comment 评论
     * @return Comment 对象
     */
    public Comment findCommentByCon(Comment comment);
}

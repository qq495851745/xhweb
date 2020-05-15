package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import com.bateng.guestroom.biz.BookCommentBiz;
import com.bateng.guestroom.biz.BookUploadBiz;
import com.bateng.guestroom.dao.BookCommentDao;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.Comment;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "bookCommentBiz")
public class BookCommentBizImpl implements BookCommentBiz {
    @Autowired
    private BookCommentDao bookCommentDao;
    @Autowired
    private BookUploadBiz bookUploadBiz;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addComment(Comment comment) {
        bookCommentDao.save(comment);
    }

    @Override
    public PageVo<Comment> findCommentAuditByPage(PageVo<Comment> pageVo, Comment comment) {
        return bookCommentDao.findCommentAuditByPage(pageVo,comment);
    }

    @Override
    public List<Comment> findAllByCommentContent(Comment comment) {
        return bookCommentDao.findAllByCommentContent(comment.getCommentContent());
    }

    @Override
    public Comment getCommentById(int id) {
        return bookCommentDao.findCommentById(id);
    }

    @Override
    @Transactional
    public void updateCommentAudit(Comment comment) {
        bookCommentDao.updateComment(comment.getRea(),comment.getFlag(),comment.getId());
    }

    @Override
    public List<Comment> findAllByBookId(int id) {
        return bookCommentDao.findAllByBookId(id);
    }

    @Override
    public String findBookAjax() {
        List<Book> books = bookUploadBiz.findAll();
        return JSONObject.toJSONString(books,new SerializeFilter[]{
                new PropertyFilter() {
                    @Override
                    public boolean apply(Object o, String s, Object o1) {
                        return s.equals("id") || s.equals("name")||s.equals("subject");
                    }
                },
                new NameFilter() {
                    @Override
                    public String process(Object o, String s, Object o1) {
                        if (s.equals("id"))
                            return "id";
                        else if (s.equals("subject"))
                            return "pId";
                        else if (s.equals("name"))
                            return "name";
                        else
                            return s;
                    }
                }, new ValueFilter() {
            @Override
            public Object process(Object o, String s, Object o1) {
                if (s.equals("pId"))
                    if (o1 != null)
                        return ((Subject) o1).getId();
                    else
                        return 0;
                return o1;
            }
        }
        }, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public PageVo<Comment> findLookCommentAuditByPage(PageVo<Comment> pageVo, Comment comment) {
        return bookCommentDao.findLookCommentAuditByPage(pageVo, comment);
    }

    @Override
    public Comment findCommentByCon(Comment comment) {
        List<Comment> comments =bookCommentDao.findCommentByCon(comment.getBook().getId(),comment.getCommentContent());
        return  comments.size()==0?null:comments.get(0);
//        return bookCommentDao.findCommentByCon(comment.getBook().getId(),comment.getCommentContent()).get(0);
    }
}

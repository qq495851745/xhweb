package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.BookCommentBiz;
import com.bateng.guestroom.biz.SubjectBiz;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/ajax/book")
public class BookAjaxController {
    @Autowired
    private BookCommentBiz bookCommentBiz;

    @RequestMapping(value = "findBook" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findBook(HttpSession session){
        Book book = (Book) session.getAttribute("book");
        return bookCommentBiz.findBookAjax();
    }

    @RequestMapping(value = "findBookOrSubject" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findBookOrSubject(HttpSession session){
        Subject subject = (Subject) session.getAttribute("subject");
        return bookCommentBiz.findSubjectByBookAjax();
    }
}

package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.BookAuditBiz;
import com.bateng.guestroom.biz.BookCommentBiz;
import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("guestroom")
public class BookCommentController {
    @Autowired
    private BookAuditBiz bookAuditBiz;
    @Autowired
    private BookCommentBiz bookCommentBiz;

    //跳转评论首页
    @RequestMapping(value = "/bookComment/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String commentIndex(PageVo<Book> pageVo, Book book, Model model){
        pageVo = bookAuditBiz.findBookAuditByPage(pageVo,book);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("book",book);
        model.addAttribute("subject",book.getSubject());
        return "book/book_comment_index";
    }

    //通过选择图书来评论
    @RequestMapping(value = "/bookComment/addComment/{id}",method = {RequestMethod.GET})
    public String toComment(@PathVariable("id") int id, Model model, User user,HttpServletRequest request) {
        HttpSession session= request.getSession();
        Book book = bookAuditBiz.getBookById(id);
        user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("book",book);
        return "book/book_comment_text";
    }


    //添加评论
    @RequestMapping(value = "/bookComment", method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(Comment comment) {
        JSONObject jsonObject = new JSONObject();
        comment.setFlag(0);
        comment.setCommentDate(new Date());
        if(bookCommentBiz.findCommentByCon(comment)!=null){
            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
            jsonObject.put("message", "当前评论已经存在，不能使用");
        }else {
            bookCommentBiz.addComment(comment);
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("callbackType", "closeCurrent");//关闭当前标签页
            jsonObject.put("navTabId", "w_46");
            jsonObject.put("message", "评论成功");
        }return jsonObject.toJSONString();
    }

    //跳转审核首页
    @RequestMapping(value = "/commentAudit/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String commentAuditIndex(PageVo<Comment> pageVo, Comment comment, Model model){
        pageVo = bookCommentBiz.findCommentAuditByPage(pageVo, comment);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("comment",comment);
        return "book/book_comment_audit_index";
    }

    //修改审核
    @RequestMapping(value = "/commentAudit/{id}",method = {RequestMethod.GET})
    public String toEdit(@PathVariable("id") int id, Model model) {
        Comment comment = bookCommentBiz.getCommentById(id);
        model.addAttribute("comment",comment);
        return "book/book_comment_audit_edit";
    }

    //做修改
    @RequestMapping(value = "/commentAudit",method = {RequestMethod.PUT,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doEdit(Comment comment){
        bookCommentBiz.updateCommentAudit(comment);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message", "审核完成!");
        jsonObject.put("navTabId", "w_47");
        jsonObject.put("callbackType","closeCurrent");
        return  jsonObject.toJSONString();
    }

    //跳转查看评论页面
    @RequestMapping(value = "/lookComment/index",method = {RequestMethod.GET})
    public String lookComment(Book book,Model model) {
        model.addAttribute("book",book);
        return "book/book_look_comment_index";
    }

    //通过图书id来跳转该图书的评论
    @RequestMapping(value = "/comment/lookComment/{book.id}",method = {RequestMethod.GET,RequestMethod.POST})
    public String lookComment(@PathVariable("book.id") int id,PageVo<Comment> pageVo,Comment comment, Model model){

//      List<Comment> comments = bookCommentBiz.findAllByBookId(id);
        pageVo = bookCommentBiz.findLookCommentAuditByPage(pageVo,comment);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("comment",comment);
        model.addAttribute("bid",id);
        return "book/book_look_comment";
    }
}

package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.BookUploadBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.model.WebConfig;
import com.bateng.guestroom.entity.Book;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @author 张伟金
 * @date 2020/4/30-15:42
 */
@Controller
@RequestMapping("/guestroom")
public class BookUploadController {

    @Autowired
    private BookUploadBiz bookUploadBiz;

    @RequestMapping(value = "/bookUpload/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(PageVo<Book> pageVo, Book book, Model model) {
        pageVo = bookUploadBiz.findBookByPage(pageVo,book);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("book", book);
        model.addAttribute("subject",book.getSubject());
        return "book/bookUpload_index";
    }

    @RequestMapping(value = "/bookUpload/toUpload",method = {RequestMethod.GET})
    public String toUpload(Book book, Subject subject,Model model){
        model.addAttribute("book",book);
        model.addAttribute("subject",subject);
        return "book/bookUpload";
    }

    @RequestMapping(value = "/bookUpload/toUpdate/{id}",method = {RequestMethod.GET})
    public String toUpdate(@PathVariable("id") int bid, Subject subject ,Model model){
        Book book = bookUploadBiz.findBookById(bid);
        model.addAttribute("book",book);
        model.addAttribute("subject",subject);
        return "book/book_edit";
    }
    @RequestMapping(value ="/bookUpload/toSelectSubject",method = RequestMethod.GET)
    public String toSelectSubject(){
        return "book/selectSubject";
    }

    @RequestMapping(value = "/bookUpload/doUpdate",method = RequestMethod.PUT,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doEdit(Book book){
        String path = book.getPath();
        WebConfig config = new WebConfig();
        config.setBookPath("/action/");
        path = config.getBookPath() + path;
        book.setPath(path);
        bookUploadBiz.updateBook(book);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message", "更新完成!");
        jsonObject.put("navTabId", "w_35");
        jsonObject.put("callbackType","closeCurrent");
        return  jsonObject.toJSONString();
    }

    @RequestMapping(value = "/bookUpload/upload", method =RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String upload(Book book) {
        JSONObject jsonObject = new JSONObject();
        List<Book> books = bookUploadBiz.findBookByName(book);
        if (books.size() > 0) {//这个书户已经存在
            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
            jsonObject.put("message", "当前书名已经存在，不能使用");
        } else {
            String path = book.getPath();
            WebConfig config = new WebConfig();
            config.setBookPath("/action/");
            path = config.getBookPath() + path;
            book.setPath(path);
            bookUploadBiz.uploadBook(book);
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("callbackType", "closeCurrent");//关闭当前标签页
            jsonObject.put("navTabId", "w_35");
            jsonObject.put("message", "图书上传成功");
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/bookUpload/delete/{id}",method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doDelete(@PathVariable("id") int bid){
        bookUploadBiz.deleteBookById(bid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("navTabId", "w_35");
        jsonObject.put("message", "删除成功");
        return  jsonObject.toJSONString();
    }


}

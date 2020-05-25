package com.bateng.guestroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guestroom")
public class ViewController {

    @RequestMapping(value = "/bookCommentView/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "view/bookCommentView";
    }
}

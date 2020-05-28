package com.bateng.guestroom;

import com.bateng.guestroom.biz.BookCommentBiz;
import com.bateng.guestroom.biz.BookDownloadBiz;
import com.bateng.guestroom.biz.MenuBiz;
import com.bateng.guestroom.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuBizTest {

    @Autowired
    private MenuBiz menuBiz;

    @Autowired
    private BookDownloadBiz bookDownloadBiz;

    @Autowired
    private BookCommentBiz bookCommentBiz;

    @Test
    public void getMenuByIdTest(){
    }



    public MenuBiz getMenuBiz() {
        return menuBiz;
    }

    public void setMenuBiz(MenuBiz menuBiz) {
        this.menuBiz = menuBiz;
    }
}

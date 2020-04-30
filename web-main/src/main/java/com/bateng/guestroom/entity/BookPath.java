package com.bateng.guestroom.entity;

import javax.persistence.*;

/**
 * 图书实体，保存存放的图书
 */
@Entity
@Table(name = "t_bookpath")
public class BookPath {

    @Id
    @Column(name = "bpath")
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

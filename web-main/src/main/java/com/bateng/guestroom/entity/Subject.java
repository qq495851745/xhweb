package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.io.File;

/**
 * 类别实体  图书的类目。科目  无限层级关系
 */
@Entity
@Table(name = "t_subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private  Integer id;

    @Column(name = "sname")
    private String name;

    /**
     * 科目的描述，说明
     */
    @Column(name = "sdesc")
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

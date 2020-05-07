package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.io.File;
import java.util.List;

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

    @Column(name = "delflag")
    private int flag;//删除标记


    @ManyToOne
    @JoinColumn(name = "pid")
    private Subject subject;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private List<Subject> subjects;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

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

package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    public int id;

    @Column(name = "classname")
    public String name;

    @ManyToOne
    @JoinColumn(name = "pid")
    public  Grade grade;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private List<Grade> grades;

    @Column(name = "delflag")
    public int flag;

    /*
    描述
     */
    @Column(name = "besc")
    public String zesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int falg) {
        this.flag = flag;
    }

    public String getZesc() {
        return zesc;
    }

    public void setZesc(String zesc) {
        this.zesc = zesc;
    }
}

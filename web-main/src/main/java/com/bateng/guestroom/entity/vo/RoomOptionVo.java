package com.bateng.guestroom.entity.vo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 房间部位
 */

public class RoomOptionVo {

    private Integer id;


    private String name; // 部位名称


    private int flag;//删除标记


    private Date createDate;


    private Date updateDate;


    private String desprition;//客房描述信息

    private RoomOptionVo roomOptionVo;

    private int count;//报修次数统计


    private Date time01;//用于搜索条件


    private Date time02;//用于搜索条件

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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getTime01() {
        return time01;
    }

    public void setTime01(Date time01) {
        this.time01 = time01;
    }

    public Date getTime02() {
        return time02;
    }

    public void setTime02(Date time02) {
        this.time02 = time02;
    }

    public String getDesprition() {
        return desprition;
    }

    public void setDesprition(String desprition) {
        this.desprition = desprition;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public RoomOptionVo getRoomOptionVo() {
        return roomOptionVo;
    }

    public void setRoomOptionVo(RoomOptionVo roomOptionVo) {
        this.roomOptionVo = roomOptionVo;
    }
}

package com.blog.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.blog.common.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * 留言板
 *
 * @author Leeqingyi
 * @time 2021/7/11
 */
@Entity
@Table(name = "me_board")
public class Board extends BaseEntity<Integer> {

    private static final long serialVersionUID = 734633451336613146L;


    @Column(name = "context")
    private String context;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createtime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}

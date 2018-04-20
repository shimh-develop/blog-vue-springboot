package com.shimh.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.shimh.common.entity.BaseEntity;

/**
 * 文章内容
 *
 * @author shimh
 * <p>
 * 2018年1月23日
 */
@Entity
@Table(name = "me_article_body")
public class ArticleBody extends BaseEntity<Long> {

    /**
     *
     */
    private static final long serialVersionUID = -7611409995977927628L;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    private String content; // 内容

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    private String contentHtml;


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getContentHtml() {
        return contentHtml;
    }


    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }


}

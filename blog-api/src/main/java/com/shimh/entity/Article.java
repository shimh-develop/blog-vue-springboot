package com.shimh.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.shimh.common.entity.BaseEntity;
/**
 * 文章
 * 
 * @author shimh
 *
 * 2018年1月23日
 *
 */
@Entity
@Table(name = "me_article")
public class Article extends BaseEntity<Integer>{

	private static final int Article_TOP = 1;
	
	private static final int Article_Common = 0;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4470366380115322213L;
	
	
	@Column(name = "title", length = 64)
	private String title;
	
	@Column(name = "summary", length = 50)
	private String summary;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="body_id")
	private ArticleBody body;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(name = "me_article_tag", 
			joinColumns = {@JoinColumn(name = "tag_id")}, 
	        inverseJoinColumns = {@JoinColumn(name = "article_id")})
	private List<Tag> tags;
	
	
	private int comments;

	private int views;
	
	/**
	 * 置顶
	 */
	private int weight = Article_Common;
	

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
	
	
}

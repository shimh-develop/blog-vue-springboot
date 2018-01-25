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

	public static final int Article_TOP = 1;
	
	public static final int Article_Common = 0;
	
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}


	public ArticleBody getBody() {
		return body;
	}


	public void setBody(ArticleBody body) {
		this.body = body;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public List<Tag> getTags() {
		return tags;
	}


	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}


	public int getComments() {
		return comments;
	}


	public void setComments(int comments) {
		this.comments = comments;
	}


	public int getViews() {
		return views;
	}


	public void setViews(int views) {
		this.views = views;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}

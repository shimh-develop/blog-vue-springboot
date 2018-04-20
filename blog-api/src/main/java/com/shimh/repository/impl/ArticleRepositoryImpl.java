package com.shimh.repository.impl;

import com.shimh.entity.Article;
import com.shimh.repository.wrapper.ArticleWrapper;
import com.shimh.vo.ArticleVo;
import com.shimh.vo.CategoryVO;
import com.shimh.vo.PageVo;
import com.shimh.vo.TagVO;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Article> listArticles(PageVo page) {

        StringBuilder hql = new StringBuilder("from Article");

        if (null != page.getName() && !"".equals(page.getName())) {
            hql.append(" order by ");
            hql.append(page.getName());
        }

        if (null != page.getSort() && !"".equals(page.getSort())) {
            hql.append(" ");
            hql.append(page.getSort());
        }

        Query query = getSession().createQuery(hql.toString());

        if (null != page.getPageNumber() && null != page.getPageSize()) {
            query.setFirstResult(page.getPageSize() * (page.getPageNumber() - 1));
            query.setMaxResults(page.getPageSize());
        }

        return query.list();

    }

    @Override
    public List<Article> listArticles(ArticleVo article, PageVo page) {

        StringBuilder hql = new StringBuilder("from Article a ");

        if (null != article.getTagId()) {

            hql.append(" inner join fetch a.tags t");
        }

        hql.append(" where 1=1 ");

        if (null != article.getCategoryId()) {

            hql.append(" and a.category.id = :categoryId");
        }

        if (null != article.getTagId()) {

            hql.append(" and t.id = :tagId");
        }


        if (null != article.getYear() && !"".equals(article.getYear())) {
            hql.append(" and YEAR(a.createDate) = :year");
        }

        if (null != article.getMonth() && !"".equals(article.getMonth())) {
            hql.append(" and MONTH(a.createDate) = :month");
        }

        if (null != page.getName() && !"".equals(page.getName())) {
            hql.append(" order by ");
            hql.append(page.getName());
        }

        if (null != page.getSort() && !"".equals(page.getSort())) {
            hql.append(" ");
            hql.append(page.getSort());
        }


        Query query = getSession().createQuery(hql.toString());


        if (null != article.getYear() && !"".equals(article.getYear())) {
            query.setParameter("year", article.getYear());
        }

        if (null != article.getMonth() && !"".equals(article.getMonth())) {
            query.setParameter("month", article.getMonth());
        }

        if (null != article.getTagId()) {
            query.setParameter("tagId", article.getTagId());
        }

        if (null != article.getCategoryId()) {
            query.setParameter("categoryId", article.getCategoryId());
        }

        if (null != page.getPageNumber() && null != page.getPageSize()) {
            query.setFirstResult(page.getPageSize() * (page.getPageNumber() - 1));
            query.setMaxResults(page.getPageSize());
        }

        return query.list();

//        StringBuilder hql = new StringBuilder("select a.* from me_article a ");
//
//        if (null != article.getTagId()) {
//
//            hql.append(" left join me_article_tag at on at.article_id = a.id");
//        }
//
//        hql.append(" where 1=1 ");
//
//        if (null != article.getCategoryId()) {
//
//            hql.append(" and a.category_id = :categoryId");
//        }
//
//        if (null != article.getTagId()) {
//
//            hql.append(" and at.tag_id = :tagId");
//        }
//
//
//        if (null != article.getYear() && !"".equals(article.getYear())) {
//            hql.append(" and YEAR(a.createDate) = :year");
//        }
//
//        if (null != article.getMonth() && !"".equals(article.getMonth())) {
//            hql.append(" and MONTH(a.createDate) = :month");
//        }
//
//        if (null != page.getName() && !"".equals(page.getName())) {
//            hql.append(" order by ");
//            hql.append(page.getName());
//        }
//
//        if (null != page.getSort() && !"".equals(page.getSort())) {
//            hql.append(" ");
//            hql.append(page.getSort());
//        }
//
//
//        SQLQuery query = getSession().createSQLQuery(hql.toString());
//
//
//        if (null != article.getYear() && !"".equals(article.getYear())) {
//            query.setParameter("year", article.getYear());
//        }
//
//        if (null != article.getMonth() && !"".equals(article.getMonth())) {
//            query.setParameter("month", article.getMonth());
//        }
//
//        if (null != article.getTagId()) {
//            query.setParameter("tagId", article.getTagId());
//        }
//
//        if (null != page.getPageNumber() && null != page.getPageSize()) {
//            query.setFirstResult(page.getPageSize() * (page.getPageNumber() - 1));
//            query.setMaxResults(page.getPageSize());
//        }
//
//        query.addEntity(Article.class);
//
//        return query.list();

    }

    @Override
    public List<ArticleVo> listArchives() {

        String sql = "select year(create_date) as year,month(create_date) as month,count(*) as count from me_article group by year(create_date),month(create_date)";

        SQLQuery query = getSession().createSQLQuery(sql);
        query.addScalar("year");
        query.addScalar("month");
        query.addScalar("count", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(ArticleVo.class));
        return query.list();
    }

    private Session getSession() {
        return em.unwrap(Session.class);
    }


}

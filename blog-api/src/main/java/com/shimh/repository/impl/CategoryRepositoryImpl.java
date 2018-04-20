package com.shimh.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.transaction.annotation.Transactional;

import com.shimh.repository.wrapper.CategoryWrapper;
import com.shimh.vo.CategoryVO;

/**
 * @author shimh
 * <p>
 * 2018年1月25日
 */
public class CategoryRepositoryImpl implements CategoryWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CategoryVO> findAllDetail() {

        String sql = "select c.*, count(a.category_id) as articles from me_category c "
                + "left join me_article a on a.category_id = c.id group by c.id";

        SQLQuery query = getSession().createSQLQuery(sql);
        query.addScalar("id");
        query.addScalar("categoryname");
        query.addScalar("description");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(CategoryVO.class));
        return query.list();

    }

    @Override
    public CategoryVO getCategoryDetail(Integer categoryId) {
        String sql = "select c.*, count(a.category_id) as articles from me_category c "
                + "left join me_article a on a.category_id = c.id where c.id = :categoryId";

        SQLQuery query = getSession().createSQLQuery(sql);

        query.setInteger("categoryId", categoryId);

        query.addScalar("id");
        query.addScalar("categoryname");
        query.addScalar("description");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(CategoryVO.class));

        return (CategoryVO) query.uniqueResult();
    }

    private Session getSession() {
        return em.unwrap(Session.class);
    }

}

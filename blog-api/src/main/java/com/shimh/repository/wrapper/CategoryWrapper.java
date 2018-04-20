package com.shimh.repository.wrapper;

import java.util.List;

import com.shimh.vo.CategoryVO;

/**
 * @author shimh
 * <p>
 * 2018年1月25日
 */
public interface CategoryWrapper {

    List<CategoryVO> findAllDetail();

    CategoryVO getCategoryDetail(Integer categoryId);
}

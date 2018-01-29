package com.shimh.repository.wrapper;

import java.util.List;

import com.shimh.vo.CategoryVO;
/**
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
public interface CategoryWrapper{

	List<CategoryVO> findAllDetail();
}

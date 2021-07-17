package com.blog.repository.wrapper;

import com.blog.vo.TagVO;

import java.util.List;

/**
 * @author shimh
 * <p>
 * 2018年1月25日
 */
public interface TagWrapper {

    List<TagVO> findAllDetail();

    TagVO getTagDetail(Integer tagId);
}

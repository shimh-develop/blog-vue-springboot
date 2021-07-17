package com.blog.controller;

import com.blog.common.annotation.LogAnnotation;
import com.blog.common.constant.Base;
import com.blog.common.constant.ResultCode;
import com.blog.common.result.Result;
import com.blog.entity.Category;
import com.blog.service.CategoryService;
import com.blog.vo.CategoryVO;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章分类api
 *
 * @author shimh
 * <p>
 * 2018年1月25日
 */
@RestController
@RequestMapping(value = "/categorys")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @LogAnnotation(module = "文章分类", operation = "获取所有文章分类")
    public Result listCategorys() {
        List<Category> categorys = categoryService.findAll();

        return Result.success(categorys);
    }

    @GetMapping("detail")
    @LogAnnotation(module = "文章分类", operation = "获取所有文章分类，详细")
    public Result listCategorysDetail() {
        List<CategoryVO> categorys = categoryService.findAllDetail();

        return Result.success(categorys);
    }

    @GetMapping("/{id}")
    @LogAnnotation(module = "文章分类", operation = "根据id获取文章分类")
    public Result getCategoryById(@PathVariable("id") Integer id) {

        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        Category category = categoryService.getCategoryById(id);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(category);
        return r;
    }

    @GetMapping("/detail/{id}")
    @LogAnnotation(module = "文章分类", operation = "根据id获取详细文章分类，文章数")
    public Result getCategoryDetail(@PathVariable("id") Integer id) {

        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        CategoryVO category = categoryService.getCategoryDetail(id);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(category);
        return r;
    }

    @PostMapping("/create")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "文章分类", operation = "添加文章分类")
    public Result saveCategory(@Validated @RequestBody Category category) {

        Integer categoryId = categoryService.saveCategory(category);

        Result r = Result.success();
        r.simple().put("categoryId", categoryId);
        return r;
    }

    @PostMapping("/update")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "文章分类", operation = "修改文章分类")
    public Result updateCategory(@RequestBody Category category) {
        Result r = new Result();

        if (null == category.getId()) {
            r.setResultCode(ResultCode.USER_NOT_EXIST);
            return r;
        }

        Integer categoryId = categoryService.updateCategory(category);

        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("categoryId", categoryId);
        return r;
    }

    @GetMapping("/delete/{id}")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "文章分类", operation = "删除文章分类")
    public Result deleteCategoryById(@PathVariable("id") Integer id) {
        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        categoryService.deleteCategoryById(id);

        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }


}

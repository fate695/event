package com.southwind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.pojo.Category;
import com.southwind.pojo.vo.CategoryDetailVO;
import com.southwind.service.CategoryService;
import com.southwind.mapper.CategoryMapper;
import com.southwind.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
* @author lpj
* @description 针对表【category】的数据库操作Service实现
* @createDate 2026-11-02 14:56:10
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Override
    public boolean add(String categoryName, String categoryAlias) {
        // 分类名称已被占用
        long nameCount = count(new LambdaQueryWrapper<Category>()
                .eq(Category::getCategoryName, categoryName));
        if (nameCount > 0) {
            throw new IllegalArgumentException("分类名称已被占用");
        }
        // 分类别名已被占用
        long aliasCount = count(new LambdaQueryWrapper<Category>()
                .eq(Category::getCategoryAlias, categoryAlias));
        if (aliasCount > 0) {
            throw new IllegalArgumentException("分类别名已被占用");
        }
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryAlias(categoryAlias);
        return save(category);
    }

    @Override
    public CategoryDetailVO getDetail(Integer id) {
        Category category = getById(id);
        if (category == null) {
            return null;
        }
        CategoryDetailVO vo = new CategoryDetailVO();
        vo.setCategoryName(category.getCategoryName());
        vo.setCategoryAlias(category.getCategoryAlias());
        return vo;
    }

    @Override
    public boolean update(Integer id, String categoryName, String categoryAlias) {
        Category category = getById(id);
        if (category == null) {
            return false;
        }
        // 名称被其他分类占用（排除自己）
        long nameCount = count(new LambdaQueryWrapper<Category>()
                .eq(Category::getCategoryName, categoryName)
                .ne(Category::getId, id));
        if (nameCount > 0) {
            throw new IllegalArgumentException("分类名称已被占用");
        }
        long aliasCount = count(new LambdaQueryWrapper<Category>()
                .eq(Category::getCategoryAlias, categoryAlias)
                .ne(Category::getId, id));
        if (aliasCount > 0) {
            throw new IllegalArgumentException("分类别名已被占用");
        }
        category.setCategoryName(categoryName);
        category.setCategoryAlias(categoryAlias);
        return updateById(category);
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }
        return removeById(id);
    }


}





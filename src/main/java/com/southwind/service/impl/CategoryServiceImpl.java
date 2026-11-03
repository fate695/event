package com.southwind.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.pojo.Category;
import com.southwind.service.CategoryService;
import com.southwind.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author lpj
* @description 针对表【category】的数据库操作Service实现
* @createDate 2026-11-02 14:56:10
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}





package com.southwind.service;

import com.southwind.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.pojo.vo.CategoryDetailVO;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

/**
* @author lpj
* @description 针对表【category】的数据库操作Service
* @createDate 2026-11-02 14:56:10
*/
@Service
public interface CategoryService extends IService<Category> {
    //新增文章分类
    boolean add(String categoryName, String categoryAlias);
    //id获取分类信息
    CategoryDetailVO getDetail(Integer id);
    //id修改分类信息
    boolean update(Integer id, String categoryName, String categoryAlias);
    //id删除分类信息
    boolean delete(Integer id);
}

package com.southwind.service;

import com.southwind.pojo.Article;
import com.southwind.pojo.PageBean;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.pojo.dto.ArticleAddDTO;
import com.southwind.pojo.dto.ArticleUpdateDTO;
import jakarta.validation.Valid;

/**
* @author lpj
* @description 针对表【article】的数据库操作Service
* @createDate 2026-11-02 14:56:10
*/
public interface ArticleService extends IService<Article> {

    boolean add(ArticleAddDTO dto);

    /**
     * 条件分页查询当前用户的文章列表
     *
     * @param pageNum    当前页码
     * @param pageSize   每页条数
     * @param categoryId 文章分类ID（可选）
     * @param state      发布状态（可选）
     */
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    boolean update(@Valid ArticleUpdateDTO dto);
}

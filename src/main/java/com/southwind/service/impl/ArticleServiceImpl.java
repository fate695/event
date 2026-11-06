package com.southwind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.pojo.Article;
import com.southwind.pojo.PageBean;
import com.southwind.pojo.dto.ArticleAddDTO;
import com.southwind.pojo.dto.ArticleUpdateDTO;
import com.southwind.service.ArticleService;
import com.southwind.mapper.ArticleMapper;
import com.southwind.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
* @author lpj
* @description 针对表【article】的数据库操作Service实现
* @createDate 2026-11-02 14:56:10
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Override
    public boolean add(ArticleAddDTO dto) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setCoverImg(dto.getCoverImg());
        article.setState(dto.getState());
        article.setCategoryId(dto.getCategoryId());
        return save(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 获取当前登录用户ID，只能查看自己的文章
        Integer userId = TokenUtils.getUserId();

        // 条件查询
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<Article>()
                .eq(Article::getCreateUser, userId)
                .eq(categoryId != null, Article::getCategoryId, categoryId)
                .eq(state != null && !state.isEmpty(), Article::getState, state)
                .orderByDesc(Article::getCreateTime);

        // 分页查询
        Page<Article> page = page(new Page<>(pageNum, pageSize), wrapper);

        return new PageBean<>(page.getTotal(), page.getRecords());
    }

    @Override
    public boolean update(ArticleUpdateDTO dto) {
        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setCoverImg(dto.getCoverImg());
        article.setState(dto.getState());
        article.setCategoryId(dto.getCategoryId());
        return updateById(article);
    }
}





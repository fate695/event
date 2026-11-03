package com.southwind.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.pojo.Article;
import com.southwind.service.ArticleService;
import com.southwind.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author lpj
* @description 针对表【article】的数据库操作Service实现
* @createDate 2026-11-02 14:56:10
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}





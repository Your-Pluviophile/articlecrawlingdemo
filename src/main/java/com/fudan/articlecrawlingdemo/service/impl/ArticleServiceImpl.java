package com.fudan.articlecrawlingdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fudan.articlecrawlingdemo.mapper.ArticleMapper;
import com.fudan.articlecrawlingdemo.pojo.Article;
import com.fudan.articlecrawlingdemo.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linhong
 * @since 2022-11-12
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public boolean toSaveArticleList(List<Article> articleList) {
        return saveBatch(articleList);
    }
}

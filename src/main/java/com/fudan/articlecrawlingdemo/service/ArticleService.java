package com.fudan.articlecrawlingdemo.service;

import com.fudan.articlecrawlingdemo.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linhong
 * @since 2022-11-12
 */
public interface ArticleService extends IService<Article> {
    boolean toSaveArticleList(List<Article> articleList);
}

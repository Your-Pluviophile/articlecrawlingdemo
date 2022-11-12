package com.fudan.articlecrawlingdemo.service.task;

import cn.hutool.core.util.ObjectUtil;
import com.fudan.articlecrawlingdemo.pojo.Article;
import com.fudan.articlecrawlingdemo.service.ArticleService;
import com.fudan.articlecrawlingdemo.utils.HtmlParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class ArticleTask {
    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/30 * * * * ?")
    public void toSaveArtByNetEase() {
        log.info("开始爬取文章了");
        List<Article> toSaveArticles = HtmlParseUtil.getArtByNetEase();
        if (ObjectUtil.isNotEmpty(toSaveArticles)) {
            boolean saveArticleList = articleService.toSaveArticleList(toSaveArticles);
            log.info("{}时====文章保存==={}===", LocalDateTime.now(),saveArticleList);
        }
    }
}

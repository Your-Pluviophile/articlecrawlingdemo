package com.demo.article.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import com.demo.article.Constant.SystemConstant;
import com.demo.article.pojo.Article;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HtmlParseUtil {
    public static List<Article> getArtByNetEase() {
        //目标 爬取 网易新闻文章链接
        //请求url
        String url = "https://news.163.com";
        ArrayList<Article> articles = new ArrayList<>();
        try {
            //解析网页
            Document document = Jsoup.parse(new URL(url), SystemConstant.NUM_5000);
            //获取头部新闻
            Element jsTopNews = document.getElementById("js_top_news");

            assert jsTopNews != null;
            Elements elementsByTag = jsTopNews.getElementsByTag("li");
            //温馨提示:爬取网易新闻全部资源 需要二次爬取(爬第一次的链接后再爬取获取作者 时间 图片) 对于网易导入的外部资源链接(无法处理) 爬取少量信息
            for (Element el : elementsByTag) {
                //文章链接
                String articleUrl = el.getElementsByTag("a").eq(SystemConstant.NUM_ZERO).attr("href");
                //文章标题
                String title = el.getElementsByTag("a").eq(SystemConstant.NUM_ZERO).text();
                //进入文章 链接里 获取文章作者和图片
                Document articleContext = Jsoup.parse(new URL(articleUrl), SystemConstant.NUM_5000);
                //获取文章作者
                String articleSource = articleContext.getElementsByClass("post_info").eq(SystemConstant.NUM_ZERO).text();
                //如果文章中获取不了具体信息 说明不是网易文章 可直接跳过
                if (ObjectUtil.isEmpty(articleSource)) {
                    continue;
                }
                //截取出文章作者
                String author = StrUtil.sub(articleSource, 24, -6);
                //文章发表时间
                LocalDateTime gmtModified = DateUtil.parseLocalDateTime(StrUtil.sub(articleSource, 0, 19), SystemConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                //获取文章图片
                Element content = articleContext.getElementById("content");

                assert content != null;
                String imgUrl = content.getElementsByTag("img").eq(SystemConstant.NUM_ZERO).attr("src");

                articles.add(new Article().setArticleName(title).setArticleAuthor(author).setArticleShowPic(imgUrl).setArticleUrl(articleUrl).setGmtCreate(gmtModified));
            }
            //对数组 去重处理 以后万一 网易新闻 页面容易重复新闻展示 北京新闻 全球新闻 有些会重复
            articles = CollUtil.distinct(articles);
            articles.forEach(toSaveArticle ->
            {
                log.info("文章" + toSaveArticle);
            });
        } catch (IOException | NullPointerException e) {
            log.info("文章爬取失败====={}====", e);
        }
        return articles;
    }
}

package com.fudan.articlecrawlingdemo.mapper;

import com.fudan.articlecrawlingdemo.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linhong
 * @since 2022-11-12
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}

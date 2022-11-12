package com.fudan.articlecrawlingdemo.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author linhong
 * @since 2022-11-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("article")
@Accessors(chain = true)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章Id
     */
    @TableId("pk_id")
    private Long pkId;

    /**
     * 文章名称
     */
    @TableField("article_name")
    private String articleName;

    /**
     * 文章作者
     */
    @TableField("article_author")
    private String articleAuthor;

    /**
     * 创造时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 文章爬取链接
     */
    @TableField("article_url")
    private String articleUrl;

    /**
     * 文章展示图片url
     */
    @TableField("article_show_pic")
    private String articleShowPic;


}

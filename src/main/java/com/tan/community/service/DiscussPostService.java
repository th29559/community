package com.tan.community.service;

import com.tan.community.dao.DiscussPostMapper;
import com.tan.community.entity.DiscussPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * @author 谭浩
 * @version 1.0
 */
@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper  discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offSet, int limit){
        return discussPostMapper.selectDiscussPosts(userId, offSet, limit);
    }
    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }
    public int addDiscussPost(DiscussPost post) {
        if (post == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 转义HTML标记
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));

        return discussPostMapper.insertDiscussPost(post);
    }
    public DiscussPost findDiscussPostById(int id) {
        return discussPostMapper.selectDiscussPostById(id);
    }

    public int updateCommentCount(int id, int commentCount) {
        return discussPostMapper.updateCommentCount(id, commentCount);
    }
}

package com.cuiuc.service;

import com.cuiuc.dao.BlogDao;
import com.cuiuc.entity.Blog;

import java.util.List;

public class BlogService {
    BlogDao blogDao = new BlogDao();

    public List<Blog> getBlogsList() {
        return blogDao.getBlogsList();
    }

    public Blog getBlogById(int id) {
        return blogDao.getBlogById(id);
    }

    public List<Blog> getBlogLikeTitleOrSummary(String titleOrSummary) {
        return blogDao.getBlogLikeTitleOrSummary(titleOrSummary);
    }

    public int addBlog(Blog blog) {
        return blogDao.addBlog(blog);
    }

    public List<Blog> getBlogAndTypeList() {
        return blogDao.getBlogAndTypeList();
    }

    public int updateWeblog(Blog blog) {
        return blogDao.updateWeblog(blog);
    }

    public int deleteBlog(int id) {
        return blogDao.deleteBlog(id);
    }

    public List<Blog> getBlogDateCount() {
        return blogDao.getBlogDateCount();
    }

    public List<Blog> getBlogByDate(String date) {
        return blogDao.getBlogByDate(date);
    }

    public void updateClickHit(int id) {
        blogDao.updateClickHit(id);
    }

    public void updateReplyHit(int blogId) {
        blogDao.updateReplyHit(blogId);
    }
}

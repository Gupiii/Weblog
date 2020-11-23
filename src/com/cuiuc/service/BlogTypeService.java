package com.cuiuc.service;

import com.cuiuc.dao.BlogTypeDao;
import com.cuiuc.entity.Blog;
import com.cuiuc.entity.BlogType;

import java.util.List;

public class BlogTypeService {
    BlogTypeDao blogTypeDao = new BlogTypeDao();
    public List<BlogType> BlogTypeCountList() {
        return  blogTypeDao.BlogTypeCountList();
    }

    public List<BlogType> getBlogTypeCount() {
        return blogTypeDao.getBlogTypeCount();
    }

    public int addBlogType(BlogType blogType) {
        return blogTypeDao.addBlogType(blogType);
    }

    public int deleteBlogType(int ids) {
        return blogTypeDao.deleteBlogType(ids);
    }

    public int updateBlogType(BlogType blogType) {
        return blogTypeDao.updateBlogType(blogType);
    }

    public List<Blog> getBlogByType(int id) {
        return blogTypeDao.getBlogByType(id);
    }
}

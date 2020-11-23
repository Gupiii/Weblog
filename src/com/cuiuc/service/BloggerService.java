package com.cuiuc.service;

import com.cuiuc.dao.BloggerDao;
import com.cuiuc.entity.Blogger;

public class BloggerService {
    BloggerDao bloggerDao = new BloggerDao();
    public Blogger login(Blogger blogger) {
        return bloggerDao.login(blogger);
    }

    public int updateBloggerPwd(Blogger blogger) {
        return bloggerDao.updateBloggerPwd(blogger);
    }

    public int updateBlogger(Blogger blogger) {
        return bloggerDao.updateBlogger(blogger);
    }

    public int registerBlogger(Blogger blogger) {
        return bloggerDao.registerBlogger(blogger);
    }
}

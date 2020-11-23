package com.cuiuc.servlet.admin;

import com.cuiuc.entity.Blog;
import com.cuiuc.service.BlogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加博文
 */
@WebServlet("/addBlogAdminServlet")
public class AddBlogAdminServlet extends HttpServlet {
    BlogService blogService = new BlogService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String blogTypeId = req.getParameter("blogType.id");
        String content = req.getParameter("content");
        String summary = req.getParameter("summary");
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setTypeId(Integer.parseInt(blogTypeId));
        blog.setContent(content);
        blog.setSummary(summary);
        int i = blogService.addBlog(blog);
        if (i>0){
            resp.getWriter().print("true");
        } else {
            resp.getWriter().print("false");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

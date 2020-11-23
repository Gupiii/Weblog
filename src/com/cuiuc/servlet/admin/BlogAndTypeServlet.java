package com.cuiuc.servlet.admin;

import com.cuiuc.entity.Blog;
import com.cuiuc.service.BlogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/blogAndTypeServlet")
public class BlogAndTypeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("test/html;charset=utf-8");
        String way = req.getParameter("way");
        if (way.equals("getBlogAndTypeList")){
            this.getBlogAndTypeList(req, resp);
        } else if (way.equals("getUpdateBlogByIda")){
            this.getUpdateBlogByIda(req, resp);//修改前查询
        } else if (way.equals("updateWeblog")){
            this.updateWeblog(req, resp);
        } else if (way.equals("deleteBlog")){
            this.deleteBlog(req, resp);
        }
        /* else if (way.equals("getBlogByIdComment")){
            this.getBlogByIdComment(req ,resp);
        }*/
    }

/*    private void getBlogByIdComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Blog Blogcomment = blogService.getBlogById(Integer.parseInt(id));
        HttpSession session = req.getSession();
        session.setAttribute("Blogcomment",Blogcomment);
        req.getRequestDispatcher("/admin/commentManage.jsp").forward(req, resp);
    }*/

    private void deleteBlog(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idInfo = req.getParameter("ids");

        String[] idsArr = idInfo.split(",");
//        Integer[] ids=new Integer[idsArr.length];//这一步是将string类型的数组转换为int类型的数组
        int i = 0;
        for (String id : idsArr) {
            i = blogService.deleteBlog(Integer.parseInt(id));
        }
        if (i>0){
            resp.getWriter().print("true");
        } else {
            resp.getWriter().print("false");
        }
    }

    //修改提交
    private void updateWeblog(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String blogTypeId = req.getParameter("blogType.id");
        String summary = req.getParameter("summary");
        String content = req.getParameter("content");
        Blog blog = new Blog();
        blog.setId(Integer.parseInt(id));
        blog.setTitle(title);
        blog.setTypeId(Integer.parseInt(blogTypeId));
        blog.setContent(content);
        blog.setSummary(summary);
        int i = blogService.updateWeblog(blog);
        if (i>0){
            resp.getWriter().print("true");
        } else {
            resp.getWriter().print("false");
        }
    }

    //后台指定id查询（修改前查询）
    private void getUpdateBlogByIda(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Blog updateBlog = blogService.getBlogById(Integer.parseInt(id));
        HttpSession session = req.getSession();
        session.setAttribute("updateBlog",updateBlog);
        req.getRequestDispatcher("/admin/modifyBlog.jsp").forward(req,resp);
    }

    BlogService blogService = new BlogService();
    //后台查询所有博文信息
    private void getBlogAndTypeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Blog> blogAndTypeList = blogService.getBlogAndTypeList();
        HttpSession session = req.getSession();
        session.setAttribute("blogAndTypeList",blogAndTypeList);
        req.getRequestDispatcher("/admin/blogManage.jsp").forward(req, resp);
    }

}

package com.cuiuc.servlet;

import com.cuiuc.entity.Blog;
import com.cuiuc.entity.BlogType;
import com.cuiuc.entity.Comment;
import com.cuiuc.entity.Link;
import com.cuiuc.service.BlogService;
import com.cuiuc.service.BlogTypeService;
import com.cuiuc.service.CommentService;
import com.cuiuc.service.LinkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/blogServlet")
public class BlogServlet extends HttpServlet {
    BlogService blogService = new BlogService();
    BlogTypeService blogTypeService = new BlogTypeService();
    LinkService linkService = new LinkService();
    CommentService commentService = new CommentService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("test/html;charset=utf-8");
        String way= req.getParameter("way");
        if(way.equals("getBlogsList")){
            this.getBlogsList(req, resp);//获取所有blog信息
        } else if(way.equals("getBlogById")){
            this.getBlogById(req, resp);//获取指定id blog信息
        } else if(way.equals("getBlogLikeTitleOrSummary")){
            this.getBlogLikeTitleOrSummary(req, resp); //模糊查询，搜索
        } else if (way.equals("getBlogByDate")){
            this.getBlogByDate(req, resp);
        }
    }

    private void getBlogByDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        System.out.println(""+date);
//        new Blog().setReleaseDate(.date);
        List<Blog> blogByDate = blogService.getBlogByDate(date);
//        HttpSession session = req.getSession();
        req.setAttribute("blogList",blogByDate);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    //模糊查询根据title or summary
    private void getBlogLikeTitleOrSummary(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titleOrSummary = req.getParameter("titleOrSummary");
//        System.out.println(""+titleOrSummary);
        List<Blog> blogLikeList = blogService.getBlogLikeTitleOrSummary(titleOrSummary);
        HttpSession session = req.getSession();
        session.setAttribute("blogLikeList",blogLikeList);
        req.getRequestDispatcher("/cuiuc/blog_like.jsp").forward(req, resp);
    }

    //根据ID获取指定博文信息
    private void getBlogById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Blog blog = blogService.getBlogById(Integer.parseInt(id));//Integer.parseInt(id)转为int类型
        HttpSession session = req.getSession();
        session.setAttribute("blog",blog);

        //将指定Id的博文评论获取出来
        List<Comment> commentByBlogId = commentService.getCommentByBlogId(Integer.parseInt(id));
        session.setAttribute("commentByBlogId",commentByBlogId);

        //点击量+1
        blogService.updateClickHit(Integer.parseInt(id));

        req.getRequestDispatcher("/cuiuc/blog_info.jsp").forward(req, resp);
    }
    //获取所有blog信息
    private void getBlogsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Blog> blogList = new ArrayList<>();
//
        //获取所有blog信息
        try {
            blogList = blogService.getBlogsList();
            //空判断
            if (blogList.size()!=0){
                int blogId=0;
                //遍历list集合
                for (int i=0;i<blogList.size();i++){
                    //取出，每一条记录
                    Blog blog = blogList.get(i);
                    blogId = blog.getId();
//                    System.out.println(""+blogId);
//              修改评论个数
                    blogService.updateReplyHit(blogId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //重新获取所有博文信息
        blogList = blogService.getBlogsList();
        //如果存储在request域，必须转发到对应页面才有效（才能取到数据）
        HttpSession session = req.getSession();
        session.setAttribute("blogList",blogList);

        List<BlogType> blogTypes = blogTypeService.getBlogTypeCount();
        session.setAttribute("blogTypes",blogTypes);

        List<Blog> blogDateCount = blogService.getBlogDateCount();
        session.setAttribute("blogDateCount",blogDateCount);


        List<Link> linkList = linkService.getLinkList();
        session.setAttribute("linkList",linkList);
        //跳转到index.jsp域
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

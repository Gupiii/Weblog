package com.cuiuc.servlet;

import com.cuiuc.entity.Comment;
import com.cuiuc.service.CommentService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/commentServlet")
public class CommentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("test/html;charset=utf-8");
        String way = req.getParameter("way");
        if (way.equals("getCommentList")) {
            this.getCommentList(req, resp);
        } else if (way.equals("deleteComment")){
            this.deleteComment(req,resp);
        } else if (way.equals("getCommentState")){
            this.getCommentState(req, resp);
        } else if (way.equals("updateCommentState")){
            this.updateCommentState(req, resp);
        } else if (way.equals("addComment")){
            this.addComment(req, resp);
        } else if (way.equals("getCommentByBlogId")){
            this.getCommentByBlogId(req, resp);
        }

    }
    //查询指定BlogId的评论并且状态不为0的
    private void getCommentByBlogId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Comment> commentByBlogId = commentService.getCommentByBlogId(Integer.parseInt(id));
        HttpSession session = req.getSession();
        session.setAttribute("commentByBlogId",commentByBlogId);
        req.getRequestDispatcher("/cuiuc/blog_info.jsp").forward(req, resp);
    }

    //发布评论
    private void addComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = req.getParameter("content");
        String blogId = req.getParameter("blog.id");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBlogId(Integer.parseInt(blogId));
        int i = commentService.addComment(comment);
        JSONObject jsonObject = new JSONObject();
        if (i>0) {
            jsonObject.put("success","true");
        } else {
            jsonObject.put("success","false");
        }
        resp.getWriter().print(jsonObject);
    }

    //修改评论状态为0的
    private void updateCommentState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idInfo = req.getParameter("ids");
        String state = req.getParameter("state");

        String[] idsArr = idInfo.split(",");
//        Integer[] ids=new Integer[idsArr.length];//这一步是将string类型的数组转换为int类型的数组
        int i = 0;
        Comment comment = new Comment();
        for (String id : idsArr) {
            comment.setId(Integer.parseInt(id));
            comment.setState(Integer.parseInt(state));
             i = commentService.updateCommentState(comment);
        }

        /*String state = req.getParameter("state");
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(ids));
        comment.setState(Integer.parseInt(state));
        int i = commentService.updateCommentState(comment);*/
        JSONObject job = new JSONObject();
        if (i>0) {
            job.put("success","true");
        }else{
            job.put("success","false");
            job.put("errorInfo","发布失败");
        }
        resp.getWriter().print(job);
    }

    //查询评论状态为0的
    private void getCommentState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String state = req.getParameter("state");
        List<Comment> commentState = commentService.getCommentState(/*Integer.parseInt(state)*/);
        HttpSession session = req.getSession();
        session.setAttribute("commentState",commentState);
        req.getRequestDispatcher("/admin/commentReview.jsp").forward(req, resp);

    }

    //删除博文评论
    private void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idInfo = req.getParameter("ids");
//        System.out.println(""+ids);
        String[] idsArr = idInfo.split(",");
//        Integer[] ids=new Integer[idsArr.length];//这一步是将string类型的数组转换为int类型的数组
        int i = 0;
        for (String id : idsArr) {
            i = commentService.deleteComment(Integer.parseInt(id));
        }
        if (i>0){
            resp.getWriter().print("true");
        } else {
            resp.getWriter().print("false");
        }
    }

    CommentService commentService = new CommentService();
    //查询评论列表
    private void getCommentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Comment> commentList = commentService.getCommentList();
        HttpSession session = req.getSession();
        session.setAttribute("commentList",commentList);

        /*String id = req.getParameter("id");
        BlogService blogService = new BlogService();
        Blog Blogcomment = blogService.getBlogById(Integer.parseInt(id));
        session.setAttribute("Blogcomment",Blogcomment);*/

        req.getRequestDispatcher("/admin/commentManage.jsp").forward(req, resp);
    }
}

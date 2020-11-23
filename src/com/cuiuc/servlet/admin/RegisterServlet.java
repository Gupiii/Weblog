package com.cuiuc.servlet.admin;

import com.cuiuc.entity.Blogger;
import com.cuiuc.service.BloggerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html,charset=uft-8");

        String way = req.getParameter("way");
        if (way.equals("registerBlogger")) {
            this.registerBlogger(req, resp);
        }
    }

    BloggerService bloggerService = new BloggerService();
    private void registerBlogger(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String nickName = req.getParameter("nickName");
        String sign = req.getParameter("sign");
        String pwd = req.getParameter("pwd");
        String imageName = req.getParameter("imageName");
        String profile = req.getParameter("profile");
        Blogger blogger = new Blogger(userName,pwd,profile,nickName,sign,imageName);

        int i = bloggerService.registerBlogger(blogger);
        if (i>0) {
            req.setAttribute("message","注册成功，请登录!!!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }else{
            req.setAttribute("message","注册失败，请重新注册!!!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }

    }
}

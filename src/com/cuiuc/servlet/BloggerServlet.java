package com.cuiuc.servlet;

import com.cuiuc.entity.BlogType;
import com.cuiuc.entity.Blogger;
import com.cuiuc.service.BlogTypeService;
import com.cuiuc.service.BloggerService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/bloggerServlet")
public class BloggerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String way = req.getParameter("way");
        if (way.equals("login")) {
            this.login(req, resp);
        } else if(way.equals("getBloggerById")){
            this.getBloggerById(req, resp);
        } else if(way.equals("updateBloggerPwd")){
            this.updateBloggerPwd(req, resp);
        } else if(way.equals("updateBlogger")){
            System.out.println("进入");
            this.updateBlogger(req, resp);
        }
    }

    private void updateBlogger(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String userName = req.getParameter("userName");
        String nickName = req.getParameter("nickName");
        String sign = req.getParameter("sign");
        String imageFile = req.getParameter("imageFile");
        String profile = req.getParameter("proFile");
        /*System.out.println(id+""+userName);
        System.out.println("nickName:"+nickName);
        System.out.println("sign:"+sign);
        System.out.println("imageFile:"+imageFile);
        System.out.println("profile:"+profile);*/
        Blogger blogger = new Blogger();
        blogger.setId(Integer.parseInt(id));
        blogger.setUserName(userName);
        blogger.setNickName(nickName);
        blogger.setSign(sign);
        blogger.setImageName(imageFile);
        blogger.setProfile(profile);

        int i = bloggerService.updateBlogger(blogger);
        JSONObject jsonObject = new JSONObject();
        if (i>0) {
            jsonObject.put("success","true");
            jsonObject.put("message","修改成功！！");
        } else {
            jsonObject.put("success","false");
            jsonObject.put("message","修改失败！！");
        }
        resp.getWriter().print(jsonObject);
    }

    private void updateBloggerPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String newPassword = req.getParameter("newPassword");
        Blogger blogger = new Blogger();
        blogger.setId(Integer.parseInt(id));
        blogger.setPassWord(newPassword);
        int i = bloggerService.updateBloggerPwd(blogger);
        JSONObject jsonObject = new JSONObject();
        if (i>0) {
            jsonObject.put("success","true");
        } else {
            jsonObject.put("success","false");
        }
        resp.getWriter().print(jsonObject);
    }

    //根据Id查询用户信息
    private void getBloggerById(HttpServletRequest req, HttpServletResponse resp) {

    }

    BloggerService bloggerService = new BloggerService();

    //查询登录用户名与密码是否存在
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        Blogger blogger = new Blogger();
        blogger.setUserName(userName);
        blogger.setPassWord(passWord);
        Blogger login = bloggerService.login(blogger);
        HttpSession session = req.getSession();
        session.setAttribute("currentUser",login);

        //获取博文类型
        BlogTypeService blogTypeService = new BlogTypeService();
        List<BlogType> blogTypeCountList = blogTypeService.BlogTypeCountList();
        session.setAttribute("blogTypeCountList",blogTypeCountList);

        if (userName == "" && passWord == ""){
            req.setAttribute("message","请输入用户名或密码！！！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            if (login!=null){
                req.getRequestDispatcher("/admin/main.jsp").forward(req, resp);
            } else {
                req.setAttribute("message","用户名或密码错误，请重新输入！！！");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }
}

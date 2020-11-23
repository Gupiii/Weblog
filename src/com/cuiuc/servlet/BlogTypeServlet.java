package com.cuiuc.servlet;

import com.cuiuc.entity.Blog;
import com.cuiuc.entity.BlogType;
import com.cuiuc.service.BlogTypeService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/blogTypeServlet")
public class BlogTypeServlet extends HttpServlet {
    BlogTypeService blogTypeService = new BlogTypeService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("test/html;charset=utf-8");
        resp.setContentType("application/json;charset=UTF-8");
        String way = req.getParameter("way");
        if (way.equals("getBlogTypeCountList")){
            this.BlogTypeCountList(req, resp);//查询博文类型
        } else if (way.equals("getBlogTypeList")){
            this.getBlogTypeList(req, resp);
        } else if (way.equals("addBlogType")){
            this.addBlogType(req, resp);//添加博文类型
        } else if (way.equals("deleteBlogType")){
            this.deleteBlogType(req, resp);//删除博文类型
        } else if (way.equals("updateBlogType")){
            this.updateBlogType(req, resp);//修改博文类型
        } else if (way.equals("updateBlogTypeFindId")){
//            this.updateBlogTypeFindId(req, resp);//修改前博文类型前查询
        } else if(way.equals("getBlogByType")){
            this.getBlogByType(req, resp);//根据类型查博文
        }
    }
    //根据类型查博文
    private void getBlogByType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeId = req.getParameter("typeId");
        List<Blog> blogList = blogTypeService.getBlogByType(Integer.parseInt(typeId));
        HttpSession session = req.getSession();
        session.setAttribute("blogList",blogList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    //修改博文类型
    private void updateBlogType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String typeName = req.getParameter("typeName");
        String orderNo = req.getParameter("orderNo");
        BlogType blogType = new BlogType(Integer.parseInt(id),typeName,Integer.parseInt(orderNo));
        int i = blogTypeService.updateBlogType(blogType);
        if (i>0) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
        JSONObject json = new JSONObject();   //创建Json对象
        if (i>0) {
            json.put("success","true");  //设置Json对象的属性
//            System.out.println(json.toString());  //调用toString方法将json对象转换成json字符串
            //把json数据返回给浏览器：
            json.put("success","true");
            resp.getWriter().write(json.toString());
        } else {
            json.put("success","false");
            resp.getWriter().write(json.toString());
        }
    }

    private void deleteBlogType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idInfo = req.getParameter("ids");

        String[] idsArr = idInfo.split(",");
//        Integer[] ids=new Integer[idsArr.length];//这一步是将string类型的数组转换为int类型的数组
        int i = 0;
        for (String id : idsArr) {
             i = blogTypeService.deleteBlogType(Integer.parseInt(id));
        }
        if (i>0) {
            resp.getWriter().print("true");
        } else {
            resp.getWriter().print("false");
        }

        /*for(int i=0;i<ids.length;i++){
            ids[i]=Integer.parseInt(idsArr[i]);
        }

        List<BlogType> sysUserGroup=null;
        int i=blogTypeService.deleteBlogType(ids);
//        int i = blogTypeService.deleteBlogType(Integer.parseInt(ids));*/

    }

    //添加博文类型
    private void addBlogType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String typeName = req.getParameter("typeName");
        String orderNo = req.getParameter("orderNo");
        BlogType blogType = new BlogType();
        blogType.setTypeName(typeName);
        blogType.setOrderNo(Integer.parseInt(orderNo));
        int i = blogTypeService.addBlogType(blogType);
        if (i>0) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    private void getBlogTypeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/admin/blogTypeManage.jsp").forward(req, resp);
    }

    //查询博文类型
    private void BlogTypeCountList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BlogType> blogTypeCountList = blogTypeService.BlogTypeCountList();
        HttpSession session = req.getSession();
//        ServletContext appliaction = this.getServletContext();
        session.setAttribute("blogTypeCountList",blogTypeCountList);
        req.getRequestDispatcher("/admin/blogTypeManage.jsp").forward(req, resp);
    }
}

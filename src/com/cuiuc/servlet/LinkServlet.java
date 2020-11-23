package com.cuiuc.servlet;

import com.cuiuc.entity.Link;
import com.cuiuc.service.LinkService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/linkServlet")
public class LinkServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html:charset=utf-8");
        String way = req.getParameter("way");
        if (way.equals("getLinkList")){
            this.getLinkList(req, resp);
        } else if(way.equals("addlink")){
            this.addlink(req, resp);
        } else if(way.equals("updateLinkById")){
            this.updateLinkById(req, resp);
        } else if(way.equals("deleteLink")){
            this.deleteLink(req, resp);
        }
    }

    private void deleteLink(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idInfo = req.getParameter("ids");

        String[] idsArr = idInfo.split(",");
//        Integer[] ids=new Integer[idsArr.length];//这一步是将string类型的数组转换为int类型的数组
        int i = 0;
        for (String id : idsArr) {
            i = linkService.deleteLink(Integer.parseInt(id));
        }
        JSONObject json = new JSONObject();
        if (i>0){
            json.put("success","true");
        } else {
            json.put("success","false");
        }
        resp.getWriter().print(json);
    }

    private void updateLinkById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String linkName = req.getParameter("linkName");
        String linkUrl = req.getParameter("linkUrl");
        String orderNo = req.getParameter("orderNo");
        Link link = new Link(Integer.parseInt(id),linkName,linkUrl,Integer.parseInt(orderNo));
        int i = linkService.updateLinkById(link);
        if (i>0){
            resp.getWriter().print("true");
        } else {
            resp.getWriter().print("false");
        }
    }

    private void addlink(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String linkName = req.getParameter("linkName");
        String linkUrl = req.getParameter("linkUrl");
        String orderNo = req.getParameter("orderNo");
        Link link = new Link();
        link.setLinkName(linkName);
        link.setLinkUrl(linkUrl);
        link.setOrderNo(Integer.parseInt(orderNo));
        int i = linkService.addlink(link);
        if (i>0){
            resp.getWriter().print("true");
        } else {
            resp.getWriter().print("false");
        }

    }

    LinkService linkService = new LinkService();
    private void getLinkList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Link> linkListAdmin = linkService.getLinkList();
//        System.out.println(""+linkListAdmin);
        HttpSession session = req.getSession();
        session.setAttribute("linkListAdmin",linkListAdmin);
        req.getRequestDispatcher("/admin/linkManage.jsp").forward(req, resp);
    }
}

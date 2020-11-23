package com.cuiuc.dao;

import com.cuiuc.entity.Blog;
import com.cuiuc.entity.BlogType;
import com.cuiuc.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDao {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    public List<Blog> getBlogsList() {
        List<Blog> blogList = new ArrayList<>();
        try {
             conn = DBUtils.getConnection();
            String sql = "SELECT * FROM t_blog a ORDER BY releaseDate DESC";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
                blogList.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return blogList.size()>0?blogList:null;
    }

    public Blog getBlogById(int id) {
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM t_blog a,t_blogtype b WHERE a.typeId=b.id AND a.id="+id;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                Blog blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
                blog.setBlogType(new BlogType(rs.getInt(9),rs.getString("typeName"),rs.getInt("orderNo")));
                return blog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return null;
    }

    public List<Blog> getBlogLikeTitleOrSummary(String titleOrSummary) {
        List<Blog> blogList = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM t_blog WHERE title LIKE ? OR summary LIKE ? ORDER BY releaseDate DESC ";
            pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+titleOrSummary+"%");
            pst.setString(2,"%"+titleOrSummary+"%");
            rs = pst.executeQuery();
            while (rs.next()){
                blogList.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return blogList.size()>0?blogList:null;
    }

    public int addBlog(Blog blog) {
        int i = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO t_blog(title,summary,content,typeId,clickHit,replyHit) VALUES (?,?,?,?,0,0)";
            pst = conn.prepareStatement(sql);
            pst.setString(1,blog.getTitle());
            pst.setString(2,blog.getSummary());
            pst.setString(3,blog.getContent());
            pst.setInt(4,blog.getTypeId());
             i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst);
        }
        return i;
    }

    public List<Blog> getBlogAndTypeList() {
        List<Blog> list = new ArrayList<>();
        try {
             conn = DBUtils.getConnection();
            String sql = "SELECT * FROM t_blog a,t_blogtype b WHERE a.typeId=b.id";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            while (rs.next()){
                Blog blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
                blog.setBlogType(new BlogType(rs.getInt(9),rs.getString("typeName"),rs.getInt("orderNo")));
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst, rs);
        }
        return list.size()>0?list:null;
    }

    public int updateWeblog(Blog blog) {
        int i = 0;
        try {
             conn = DBUtils.getConnection();
            String sql = "UPDATE t_blog SET title=?,summary=?,content=?,typeId=? WHERE id=?";
             pst = conn.prepareStatement(sql);
            pst.setString(1,blog.getTitle());
            pst.setString(2,blog.getSummary());
            pst.setString(3,blog.getContent());
            pst.setInt(4,blog.getTypeId());
            pst.setInt(5,blog.getId());
             i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
        return i;
    }

    public int deleteBlog(int id) {
        int i = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM t_blog WHERE id="+id;
            pst = conn.prepareStatement(sql);
            i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
        return i;
    }

    public List<Blog> getBlogDateCount() {
        List list = new ArrayList();
        try {
             conn = DBUtils.getConnection();
            //根据 天 分类
            String sql = "SELECT DATE_FORMAT(releaseDate, '%Y-%m-%d') mont, COUNT(*) coun FROM t_blog " +
                    "GROUP BY DATE_FORMAT(releaseDate, '%Y-%m-%d') ORDER BY releaseDate DESC;";
            /*String sql ="SELECT DATE(releaseDate), COUNT(*) coun FROM t_blog" +
                    "GROUP BY DATE(releaseDate) ORDER BY releaseDate DESC";*/
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            while (rs.next()){
                Blog blog = new Blog();
                blog.setReleaseDate(rs.getDate(1));
                blog.setBlogCount(rs.getInt(2));
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst, rs);
        }
        return list.size()>0?list:null;
    }

    public List<Blog> getBlogByDate(String date) {
        List<Blog> list = new ArrayList<>();
        try {
             conn = DBUtils.getConnection();
            String sql ="SELECT * FROM t_blog WHERE DATE(releaseDate)=?";
             pst = conn.prepareStatement(sql);
             pst.setString(1,date);
             rs = pst.executeQuery();
            while(rs.next()){
                Blog blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return list.size()>0?list:null;
    }

    public void updateClickHit(int id) {
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE t_blog SET clickHit=clickHit+1 WHERE id="+id;
            pst = conn.prepareStatement(sql);
            int i = pst.executeUpdate();
            if (i>0){
//                System.out.println("阅读量+1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
    }

    public void updateReplyHit(int blogId) {
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE t_blog SET replyHit=(SELECT COUNT(*) FROM t_comment WHERE blogId=? AND state=1) WHERE id="+blogId;
            pst = conn.prepareStatement(sql);
            pst.setInt(1,blogId);
            int i = pst.executeUpdate();
            if (i>0){
//                System.out.println("修改成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
    }
}

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

public class BlogTypeDao {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    public List<BlogType> BlogTypeCountList() {
        List<BlogType> blogTypes = new ArrayList<>();
        try {
             conn = DBUtils.getConnection();
            String sql = "SELECT * FROM t_blogtype ORDER BY orderNo";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            while(rs.next()){
                BlogType blogType = new BlogType(rs.getInt(1), rs.getString(2), rs.getInt(3));
                blogTypes.add(blogType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return blogTypes.size()>0?blogTypes:null;
    }

    public List<BlogType> getBlogTypeCount() {
        List<BlogType> blogTypes = new ArrayList<>();
        try {
             conn = DBUtils.getConnection();
            String sql = "SELECT b.id,b.typeName,b.orderNo,COUNT(*) blogCount FROM t_blog a,t_blogtype b WHERE a.typeId=b.id GROUP BY b.id  ";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            while(rs.next()){
                BlogType blogType = new BlogType(rs.getInt(1), rs.getString(2), rs.getInt(3));
                blogType.setBlogCount(rs.getInt("blogCount"));
                blogTypes.add(blogType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst, rs);
        }
        return blogTypes.size()>0?blogTypes:null;
    }

    public int addBlogType(BlogType blogType) {
        int i = 0;
        try {
             conn = DBUtils.getConnection();
            String sql = "INSERT INTO t_blogtype(typeName,orderNo) VALUES (?,?)";
             pst = conn.prepareStatement(sql);
            pst.setString(1,blogType.getTypeName());
            pst.setInt(2,blogType.getOrderNo());
             i = pst.executeUpdate();

            String sql2 ="select * from t_blogtype";
            pst = conn.prepareStatement(sql2);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
        return i;
    }

    public int deleteBlogType(int ids) {
        try {
             conn = DBUtils.getConnection();
             System.out.println(""+ids);
            String sql = "DELETE FROM t_blogtype WHERE id="+ids;
             pst = conn.prepareStatement(sql);
            int i = pst.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
        return 0;
    }

    public int updateBlogType(BlogType blogType) {
        int i = 0;
        try {
             conn = DBUtils.getConnection();
            String sql= "UPDATE t_blogtype SET typeName=?,orderNo=? WHERE id=?";
             pst = conn.prepareStatement(sql);
            pst.setString(1,blogType.getTypeName());
            pst.setInt(2,blogType.getOrderNo());
            pst.setInt(3,blogType.getId());
             i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
        return i;
    }

    public List<Blog> getBlogByType(int id) {
        List<Blog> list = new ArrayList<>();
        try {
             conn = DBUtils.getConnection();
            String sql= "SELECT * FROM t_blog a,t_blogtype b WHERE b.id= a.typeId AND b.id=? ORDER BY releaseDate DESC";
             pst = conn.prepareStatement(sql);
             pst.setInt(1,id);
             rs = pst.executeQuery();
            while (rs.next()){
                Blog blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
                BlogType blogType = new BlogType(rs.getInt(9), rs.getString(10), rs.getInt(11));
                blog.setBlogType(blogType);
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst, rs);
        }
        return list.size()>0?list:null;
    }
}

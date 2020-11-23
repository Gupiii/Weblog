package com.cuiuc.dao;

import com.cuiuc.entity.Blog;
import com.cuiuc.entity.Comment;
import com.cuiuc.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    public List<Comment> getCommentList() {
        List list = new ArrayList();
        try {
             conn = DBUtils.getConnection();
//            String sql = "SELECT * FROM t_comment c LEFT OUTER JOIN t_blog b ON c.blogId=b.id";
            String sql = "SELECT c.*,b.id,b.title FROM t_comment c LEFT OUTER JOIN t_blog b ON c.blogId=b.id";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            while (rs.next()){
                Comment comment = new Comment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5), rs.getInt(6));
                Blog blog = new Blog();
                blog.setId(rs.getInt(7));
                blog.setTitle(rs.getString(8));
                comment.setBlog(blog);
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return list.size()>0?list:null;
    }

    public int deleteComment(int ids) {
        int i = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM t_comment WHERE id="+ids;
            pst = conn.prepareStatement(sql);
             i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
        return i;
    }

    /*public List<Comment> getCommentState(int state) {
        List list = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT c.*,b.id,b.title FROM t_comment c INNER JOIN t_blog b ON c.blogId=b.id AND state="+state;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                Comment comment = new Comment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5), rs.getInt(6));
                Blog blog = new Blog();
                blog.setId(rs.getInt(7));
                blog.setTitle(rs.getString(8));
                comment.setBlog(blog);
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return list.size()>0?list:null;
    }*/
    public List<Comment> getCommentState() {
        List list = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT c.*,b.id,b.title FROM t_comment c INNER JOIN t_blog b ON c.blogId=b.id AND state=0";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                Comment comment = new Comment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5), rs.getInt(6));
                Blog blog = new Blog();
                blog.setId(rs.getInt(7));
                blog.setTitle(rs.getString(8));
                comment.setBlog(blog);
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return list.size()>0?list:null;
    }

    public int updateCommentState(Comment comment) {
        int i = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE t_comment SET state=? WHERE id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,comment.getState());
            pst.setInt(2,comment.getId());
            i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
        return i;
    }

    public List<Comment> getCommentByBlogId(int id) {
        List list = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM t_comment WHERE state!=0 AND blogId="+id;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                Comment comment = new Comment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5), rs.getInt(6));
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return list.size()>0?list:null;
    }

    public int addComment(Comment comment) {
        int i = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO t_comment(content,blogId,state) VALUES (?,?,0) ";
            pst = conn.prepareStatement(sql);
            pst.setString(1,comment.getContent());
            pst.setInt(2,comment.getBlogId());
            i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst);
        }
        return i;
    }
}

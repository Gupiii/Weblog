package com.cuiuc.dao;

import com.cuiuc.entity.Blogger;
import com.cuiuc.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BloggerDao {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    public Blogger login(Blogger blogger) {
//        List<Blogger> list = new ArrayList<>();
        try {
             conn = DBUtils.getConnection();
            String sql = "SELECT * FROM t_blogger WHERE userName=? AND PASSWORD=?";
             pst = conn.prepareStatement(sql);
            pst.setString(1,blogger.getUserName());
            pst.setString(2,blogger.getPassWord());
             rs = pst.executeQuery();
            if(rs.next()){
                Blogger blogger1 = new Blogger(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
//                list.add(blogger1);
                return blogger1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn, pst, rs);
        }
        return null;
    }

    public int updateBloggerPwd(Blogger blogger) {
        int i=0;
        try {
             conn = DBUtils.getConnection();
            String sql = "UPDATE t_blogger SET PASSWORD=? WHERE id="+blogger.getId();
             pst = conn.prepareStatement(sql);
             pst.setString(1,blogger.getPassWord());
             i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst);
        }
        return i;
    }

    public int updateBlogger(Blogger blogger) {
        int i=0;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE t_blogger SET userName=?,profile=?,nickName=?,SIGN=?,imageName=? WHERE id="+blogger.getId();
            pst = conn.prepareStatement(sql);
            pst.setString(1,blogger.getUserName());
            pst.setString(2,blogger.getProfile());
            pst.setString(3,blogger.getNickName());
            pst.setString(4,blogger.getSign());
            pst.setString(5,blogger.getImageName());
            i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst);
        }
        return i;
    }

    public int registerBlogger(Blogger blogger) {
        int i=0;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO t_blogger(userName,PASSWORD,profile,nickName,SIGN,imageName) VALUES (?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1,blogger.getUserName());
            pst.setString(2,blogger.getPassWord());
            pst.setString(3,blogger.getProfile());
            pst.setString(4,blogger.getNickName());
            pst.setString(5,blogger.getSign());
            pst.setString(6,blogger.getImageName());
            i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst);
        }
        return i;
    }
}

package com.cuiuc.dao;

import com.cuiuc.entity.Link;
import com.cuiuc.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinkDao {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    public List<Link> getLinkList() {
        List<Link> list = new ArrayList<>();
        try {
             conn = DBUtils.getConnection();
            String sql = "SELECT * FROM t_link ORDER BY orderNo";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            while (rs.next()){
                Link link = new Link(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(link);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst,rs);
        }
        return list.size()>0?list:null;

    }

    public int addlink(Link link) {
        int i = 0;
        try {
             conn = DBUtils.getConnection();
            String sql = "INSERT INTO t_link(linkName,linkUrl,orderNo) VALUES (?,?,?)";
             pst = conn.prepareStatement(sql);
            pst.setString(1,link.getLinkName());
            pst.setString(2,link.getLinkUrl());
            pst.setInt(3,link.getOrderNo());
             i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst);
        }
        return i;
    }

    public int updateLinkById(Link link) {
        int i = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE t_link SET linkName=?,linkUrl=?,orderNo=? WHERE id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,link.getLinkName());
            pst.setString(2,link.getLinkUrl());
            pst.setInt(3,link.getOrderNo());
            pst.setInt(4,link.getId());
            i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst);
        }
        return i;
    }

    public int deleteLink(int ids) {
        int i = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM t_link WHERE id="+ids;
            pst = conn.prepareStatement(sql);
            i = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.myClose(conn,pst);
        }
        return i;
    }
}

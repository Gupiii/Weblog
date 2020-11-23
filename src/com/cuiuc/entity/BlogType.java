package com.cuiuc.entity;

import java.util.HashSet;
import java.util.Set;

public class BlogType {
    private int id;//博文类型ID
    private String typeName;//类型名
    private int orderNo;//类型排序
    private int blogCount;//博文按类型分类个数

    private Set<Blog> blogs = new HashSet<>();

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }

    public BlogType() {
    }

    public BlogType(int id, String typeName, int orderNo) {
        this.id = id;
        this.typeName = typeName;
        this.orderNo = orderNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
}

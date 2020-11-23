package com.cuiuc.entity;

public class Link {
    private int id;
    private String linkName;
    private String linkUrl;
    private int orderNo;

    public Link() {
    }

    public Link(int id, String linkName, String linkUrl, int orderNo) {
        this.id = id;
        this.linkName = linkName;
        this.linkUrl = linkUrl;
        this.orderNo = orderNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
}

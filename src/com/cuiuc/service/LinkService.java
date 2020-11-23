package com.cuiuc.service;

import com.cuiuc.dao.LinkDao;
import com.cuiuc.entity.Link;

import java.util.List;

public class LinkService {
    LinkDao linkDao = new LinkDao();
    public List<Link> getLinkList() {
        return linkDao.getLinkList();
    }

    public int addlink(Link link) {
        return linkDao.addlink(link);
    }

    public int updateLinkById(Link link) {
        return linkDao.updateLinkById(link);
    }

    public int deleteLink(int ids) {
        return linkDao.deleteLink(ids);
    }
}
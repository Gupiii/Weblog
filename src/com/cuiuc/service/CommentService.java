package com.cuiuc.service;

import com.cuiuc.dao.CommentDao;
import com.cuiuc.entity.Comment;

import java.util.List;

public class CommentService {
    CommentDao commentDao = new CommentDao();
    public List<Comment> getCommentList() {
        return commentDao.getCommentList();
    }

    public int deleteComment(int ids) {
        return commentDao.deleteComment(ids);
    }

    /*public List<Comment> getCommentState(int state) {
        return commentDao.getCommentState(state);
    }*/
    public List<Comment> getCommentState() {
        return commentDao.getCommentState();
    }

    public int updateCommentState(Comment comment) {
        return commentDao.updateCommentState(comment);
    }

    public List<Comment> getCommentByBlogId(int id) {
        return commentDao.getCommentByBlogId(id);
    }

    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }
}

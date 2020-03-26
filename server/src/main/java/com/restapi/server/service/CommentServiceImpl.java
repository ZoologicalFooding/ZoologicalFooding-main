package com.restapi.server.service;

import com.restapi.server.dao.CommentDao;
import com.restapi.server.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    @Override
    public void addComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public Iterable<Comment> getComments() {
        return commentDao.findAll();
    }

    @Override
    public Comment getCommentById(int id) {
        return commentDao.findById(id).get();
    }

    @Override
    public void deleteCommentById(int id) {
        commentDao.delete(commentDao.findById(id).get());
    }

    @Override
    public void updateCommentById(Comment comment, int id) {
        Comment oldComment = commentDao.findById(id).get();
        oldComment.setYorum(comment.getYorum());
        oldComment.setLikeButton(comment.getLikeButton());
        commentDao.save(oldComment);
    }

    @Override
    public void likeComment(int id) {
        Comment comment = commentDao.findById(id).get();
        int like = comment.getLikeButton();
        comment.setLikeButton(like++);
        commentDao.save(comment);
    }
}

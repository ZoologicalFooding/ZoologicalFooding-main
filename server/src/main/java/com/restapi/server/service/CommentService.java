package com.restapi.server.service;

import com.restapi.server.model.Comment;

public interface CommentService {
    void addComment(Comment comment);
    Iterable<Comment> getComments();
    Comment getCommentById(int id);
    void deleteCommentById(int id);
    void updateCommentById(Comment comment,int id);
    void likeComment(int id);

}

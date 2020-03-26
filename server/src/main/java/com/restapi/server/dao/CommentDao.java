package com.restapi.server.dao;

import com.restapi.server.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentDao extends CrudRepository<Comment,Integer> {
}

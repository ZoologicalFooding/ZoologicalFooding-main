package com.restapi.server.controller;


import com.restapi.server.model.Comment;
import com.restapi.server.service.CommentService;
import com.restapi.server.service.DonatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentService commentService;
    private final DonatesService donatesService;

    @Autowired
    public CommentController(CommentService commentService, DonatesService donatesService){
        this.commentService = commentService;
        this.donatesService = donatesService;
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Comment>> getComments() {
        Iterable<Comment> comments = commentService.getComments();
        return ResponseEntity.ok(comments);
    }
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comment> getComment(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ResponseEntity.ok(comment);
    }
    @RequestMapping(value = "/editComment/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Comment> editComment(@RequestBody(required = false) Comment comment, @PathVariable int id) {
        commentService.updateCommentById(comment,id);
        return ResponseEntity.ok(comment);
    }
    @RequestMapping(value = "/deleteComment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Comment> deleteComment(@PathVariable int id){
        Comment comment = commentService.getCommentById(id);
        //DonateTable donate = donatesService.getDonatesById(commentService.getCommentById(id).getDonatesId());
        //List<Comment> list = donate.getCommentsList();
        //list.remove(comment);
        //donate.setCommentsList(list);
        commentService.deleteCommentById(id);
        return ResponseEntity.ok(comment);
    }
    @RequestMapping(value = "/likeComment/{id}", method = RequestMethod.POST)
    public ResponseEntity<Comment> likeComment(@PathVariable int id){
        commentService.likeComment(id);
        return ResponseEntity.ok(commentService.getCommentById(id));
    }
}

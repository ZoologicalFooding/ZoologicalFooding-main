package com.restapi.server.controller;


import com.restapi.server.model.Comment;
import com.restapi.server.service.CommentService;
import com.restapi.server.service.DonatesService;
import com.restapi.server.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentService commentService;
    private final DonatesService donatesService;
    private final EmailService emailService;

    @Autowired
    public CommentController(EmailService emailService,CommentService commentService, DonatesService donatesService){
        this.commentService = commentService;
        this.donatesService = donatesService;
        this.emailService = emailService;
    }
    @Autowired
    private JavaMailSender javaMailSender;

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
        SimpleMailMessage message = new SimpleMailMessage();

        //message.setTo("zoologicalfooding@gmail.com");
        //DonateTable donate = donatesService.getDonatesById(comment.getDonatesId());
        //message.setTo(donate.getDonaterMail());
        //message.setSubject(donate.getFullName()+" yaptiginiz bagisa yorum geldi!");
        //message.setText(comment.getYorum() + " seklinde bir yorum yapildi!");
        //javaMailSender.send(message);
        //Email email = new Email();
        //email.setMessageto(donate.getDonaterMail());
        //email.setSenderFullName(donate.getFullName());
        //emailService.addEmail(email);
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

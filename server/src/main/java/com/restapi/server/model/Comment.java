package com.restapi.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commentTable")
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @GeneratedValue(generator = "commentTable_generator")
    @SequenceGenerator(
            name = "commentTable_generator",
            sequenceName = "commentTable_sequence",
            initialValue = 10
    )
    private int commentId;

    @Column
    private String yorumName;
    @Column
    private String yorum;

    @Column
    private int likeButton;

    //@Column(name = "donates_id")
    //private int donatesId;

    @Column(name = "container_id")
    private int containerId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentAddTime;

}

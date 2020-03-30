package com.restapi.server.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "emailTable")
@Getter
@Setter
@ToString
public class Email {

    @Id
    @GeneratedValue(generator = "emailTable_generator")
    @SequenceGenerator(
            name = "emailTable_generator",
            sequenceName = "emailTable_sequence",
            initialValue = 10
    )
    private int email_id;

    @Column
    private String messageto;
    @Column
    private String messageSubject;
    @Column
    private String messageBody;
    @Column
    private String requestTypeMail;
    @Column
    private String senderFullName;
    @Column
    private String senderPhone;
    @Column
    private String senderMail;
    @Column
    private String mailRequestAddress;



}

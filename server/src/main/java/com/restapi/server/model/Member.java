package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table (name = "member")
@Getter
@Setter
@ToString
public class Member {
    @Id
    @GeneratedValue(generator = "member_generator")
    @SequenceGenerator(
            name = "member_generator",
            sequenceName = "member_sequence",
            initialValue = 10
    )
    private int member_id;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String address;
    @Column
    private String email;
    @Column
    private String pass;

    //@Column
    //private String date;

}

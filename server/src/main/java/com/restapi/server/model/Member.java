package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "")
@Getter
@Setter
@ToString
public class Member {
    @Id
    private int member_id;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String pass;
}

package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="role")
@Getter
@Setter
@ToString
public class Role {
    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "member_sequence",
            initialValue = 10
    )
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role")
    private String role;


}

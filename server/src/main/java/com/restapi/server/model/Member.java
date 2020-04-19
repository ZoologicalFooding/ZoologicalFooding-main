package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

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
    @Column(name = "member_id")
    private int memberID;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String address;
    @Column
    private String email;//update güncelle
    @Column
    private String username;
    @Column
    private String pass;
    @Column
    private String pointCode;
    @Column
    private String pointStr;
    @Column
    private String socialMedia;

    //@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    //@JoinColumn(name = "member_id", referencedColumnName= "member_id")
    //private List<CreditCard> creditCardList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name = "member_id"))
    private Set<Role> role;


}

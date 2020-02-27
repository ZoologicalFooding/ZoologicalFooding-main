package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "credit_card")
@Getter
@Setter
@ToString
public class CreditCard {

    @Id
    @Column(name="card_number")
    private int cardNumber;
    @Column
    private String fullName;
    @Column
    private int cvvNumber;
    @Column
    private int expiration_date;

    @Column(name="member_id")
    private int memberId;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "card_number", referencedColumnName= "card_number")
    private List<Fills> fillsList = new ArrayList<>();
}

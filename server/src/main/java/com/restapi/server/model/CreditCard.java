package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
@Getter
@Setter
@ToString
public class CreditCard {

    @Id
    private int cardNumber;
    @Column
    private String fullName;
    @Column
    private int cvvNumber;
    @Column
    private int expiration_date;

    @Column(name="member_id")
    private int memberId;


}

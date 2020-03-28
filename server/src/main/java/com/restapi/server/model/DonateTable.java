package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donateTable")
@Getter
@Setter
@ToString
public class DonateTable {
    @Id
    @GeneratedValue(generator = "donateTable_generator")
    @SequenceGenerator(
            name = "donateTable_generator",
            sequenceName = "donateTable_sequence",
            initialValue = 10
    )
    @Column(name = "donates_id")
    private int donatesID;
    @Column
    private String foodType;
    @Column
    private String amount;
    @Column
    private int liked;

    @Column(name = "container_id")
    private int containerId;

    @Column
    private String donateType;

    @Column
    private String promotionCode;

    @Column
    private int creditCardNumber;

    @Column
    private String fullName;

    @Column
    private int expiration_date;

    @Column
    private int cvvNumber;

    @Column
    private String recieverName;

    @Column
    private String IBAN;




    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "donates_id", referencedColumnName= "donates_id")
    private List<Comment> commentsList = new ArrayList<>();


}

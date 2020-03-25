package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private int donates_id;
    @Column
    private String foodType;
    @Column
    private String commet;
    @Column
    private int liked;
    @Column
    private String comment;

    @Column(name = "container_id")
    private int containerId;

    @Column
    private int creditCardNumber;
    @Column
    private String fullName;
    @Column
    private int expiration_date;
    @Column
    private int cvvNumber;


}

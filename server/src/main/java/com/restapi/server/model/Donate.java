package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "donates")
@Getter
@Setter
@ToString
public class Donate {
    @Id
    @GeneratedValue(generator = "donates_generator")
    @SequenceGenerator(
            name = "donates_generator",
            sequenceName = "donates_sequence",
            initialValue = 10
    )
    private int donates_id;
    @Column
    private String foodType;
    @Column
    private String commet;

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

package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ProCodeTable")
@Getter
@Setter
@ToString
public class ProCodeTable {

    @Id
    @GeneratedValue(generator = "ProCodeTable_generator")
    @SequenceGenerator(
            name = "ProCodeTable_generator",
            sequenceName = "ProCodeTable_sequence",
            initialValue = 10
    )
    @Column(name = "proCode_id")
    private int proCodeID;
    @Column
    private int proCodeCol;

    @Column
    private int valid;
}

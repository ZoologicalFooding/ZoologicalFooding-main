package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "fills")
@Getter
@Setter
@ToString
public class Fills {
    @Id
    @GeneratedValue(generator = "fills_generator")
    @SequenceGenerator(
            name = "fills_generator",
            sequenceName = "fills_sequence",
            initialValue = 10
    )
    private int fills_id;
    @Column
    private String foodType;
    @Column
    private String commet;




}

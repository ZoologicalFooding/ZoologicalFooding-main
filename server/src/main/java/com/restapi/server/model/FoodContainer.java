package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="food_container")
@Getter
@Setter
@ToString
public class FoodContainer {
    @Id
    @GeneratedValue(generator = "foodcontainer_generator")
    @SequenceGenerator(
            name = "foodcontainer_generator",
            sequenceName = "foodcontainer_sequence",
            initialValue = 10
    )
    private int container_id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private int lng;
    @Column
    private int lat;
    @Column
    private String address;
    @Column
    private double weight;
}


package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name="container_id")
    private int containerID;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String longitude;
    @Column
    private String latitude;
    @Column
    private String address;
    @Column
    private double weight;
    @Column
    private String IP;
    @Column
    private String Region;
    @Column
    private String Country;
    @Column
    private String City;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "container_id", referencedColumnName= "container_id")
    private List<Donate> donatesList = new ArrayList<>();
    //@Column
    //private String date;
}


package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="food_container")
@Getter
@Setter
@ToString
public class FoodContainer {
    @Id
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


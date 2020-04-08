package com.restapi.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "discountTable")
@Getter
@Setter
@ToString
public class DiscountCodeTable {

    @Id
    @GeneratedValue(generator = "discountTable_generator")
    @SequenceGenerator(
            name = "discountTable_generator",
            sequenceName = "discountTable_sequence",
            initialValue = 10
    )
    @Column(name = "discount_id")
    private int discountID;
    @Column
    private int disCodeCol;
    @Column
    private int validCol;

}

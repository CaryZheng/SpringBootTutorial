package com.zzb.tutorial.datajpademo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "goods_order")
public class GoodsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column
    private String description;

    @Column
    private BigDecimal price;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}

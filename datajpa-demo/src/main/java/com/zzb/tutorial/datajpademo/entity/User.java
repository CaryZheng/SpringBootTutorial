package com.zzb.tutorial.datajpademo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcard_id")
    private IDCard idCard;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<GoodsOrder> goodsOrderList;

    @ManyToMany
    @JoinTable(name = "user_role_mapping", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;
}

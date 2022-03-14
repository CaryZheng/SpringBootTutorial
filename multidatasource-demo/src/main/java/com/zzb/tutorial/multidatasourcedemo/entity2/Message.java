package com.zzb.tutorial.multidatasourcedemo.entity2;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String message;

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }

}
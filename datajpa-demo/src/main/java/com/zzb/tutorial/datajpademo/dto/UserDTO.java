package com.zzb.tutorial.datajpademo.dto;

import com.zzb.tutorial.datajpademo.entity.IDCard;

public class UserDTO {

    private Integer id;
    private String userName;
    private IDCard idCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public IDCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IDCard idCard) {
        this.idCard = idCard;
    }
}

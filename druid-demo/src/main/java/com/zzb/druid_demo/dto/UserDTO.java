package com.zzb.druid_demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String phone;
    private Date birthday;
    private Integer height;
    private Integer gender;
    private Integer education;
    private String avatar;
}

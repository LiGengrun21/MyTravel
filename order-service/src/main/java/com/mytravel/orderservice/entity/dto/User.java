package com.mytravel.orderservice.entity.dto;
import lombok.Data;

/**
 * @author Li Gengrun
 * @date 2024/3/29 23:06
 */

@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private int status; // status==1 means enabled

    private String role; // assume one user can only have one role in this project
}

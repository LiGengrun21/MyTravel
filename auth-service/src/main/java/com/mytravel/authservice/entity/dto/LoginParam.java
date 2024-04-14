package com.mytravel.authservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/10 10:36
 */
@Data
public class LoginParam implements Serializable {

    private String username;

    private String password;

    private String role;
}

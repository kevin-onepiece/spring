package com.foo.auth.entity;

import lombok.Data;

import java.util.List;

/**
 * @author foo
 * @since 2022-06-01
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String algorithm;

    //List<Authority> authorities;

}

package com.foo.jwtbusiness.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author foo
 * @since 2022-06-05
 */
@Data
@Accessors(chain = true)
public class User {

    private String Username;
    private String password;
    private String code;

}

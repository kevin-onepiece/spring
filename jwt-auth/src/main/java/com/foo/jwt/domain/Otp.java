package com.foo.jwt.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 * @TableName otp
 */
@TableName(value ="otp")
@Data
@Accessors(chain = true)
public class Otp implements Serializable {
    /**
     * 
     */
    @TableId
    private String username;

    /**
     * 
     */
    private String code;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
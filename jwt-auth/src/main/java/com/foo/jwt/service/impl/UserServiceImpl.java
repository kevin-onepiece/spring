package com.foo.jwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foo.jwt.domain.User;
import com.foo.jwt.service.UserService;
import com.foo.jwt.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}





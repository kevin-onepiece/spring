package com.foo.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foo.auth.entity.Authority;
import com.foo.auth.service.AuthorityService;
import com.foo.auth.mapper.AuthorityMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority>
    implements AuthorityService{

}





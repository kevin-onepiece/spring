package com.foo.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.foo.auth.entity.Product;
import com.foo.auth.service.ProductService;
import com.foo.auth.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}





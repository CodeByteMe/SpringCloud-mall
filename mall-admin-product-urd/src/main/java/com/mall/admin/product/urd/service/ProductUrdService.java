package com.mall.admin.product.urd.service;

import com.mall.common.pojo.Product;
import org.springframework.stereotype.Service;

/*
 *   作者：官宣轩
 *   日期：2020-09-26
 */
@Service
public interface ProductUrdService {

    public boolean productUpdate(Product product);  //修改商品信息

    public boolean productDel(String productId);   //删除商品

}

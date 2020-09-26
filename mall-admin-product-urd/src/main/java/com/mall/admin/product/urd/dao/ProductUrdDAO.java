package com.mall.admin.product.urd.dao;

import com.mall.common.pojo.Product;
import org.apache.ibatis.annotations.Param;

/*
 *   作者：官宣轩
 *   日期：2020-09-26
 */
public interface ProductUrdDAO {

    public boolean productUpdate(Product product);  //修改商品信息

    public boolean productDel(String productId);   //删除商品

    public boolean productStatus(@Param("productId") String productId,@Param("publishStatus") Integer publishStatus);   //修改商品上架状态

}

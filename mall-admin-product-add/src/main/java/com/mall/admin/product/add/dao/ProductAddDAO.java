package com.mall.admin.product.add.dao;

import com.mall.common.pojo.Product;

/*
 *   作者：官宣轩
 *   日期：2020-09-25
 */
public interface ProductAddDAO {

    public boolean productAdd(Product product); //商品添加

    public String searchCompanyIdByAdminUUID(String adminId); //通过用户UUID查询公司ID

}

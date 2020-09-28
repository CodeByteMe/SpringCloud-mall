package com.mall.admin.product.sku.service.impl;

import com.mall.admin.product.sku.dao.ProductSkuDAO;
import com.mall.admin.product.sku.service.ProductSkuService;
import com.mall.common.pojo.SkuStock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-26
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {

    @Resource
    private ProductSkuDAO productSkuDAO;
    public void setProductSkuDAO(ProductSkuDAO productSkuDAO) {
        this.productSkuDAO = productSkuDAO;
    }

    @Override
    public List<SkuStock> productSkuList(String productId) {
        return productSkuDAO.productSkuList(productId);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productSkuAdd(SkuStock skuStock) {
        return productSkuDAO.productSkuAdd(skuStock);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productSkuDel(String skuId) {
        return productSkuDAO.productSkuDel(skuId);
    }
}

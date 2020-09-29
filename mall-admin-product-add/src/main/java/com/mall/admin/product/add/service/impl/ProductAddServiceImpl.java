package com.mall.admin.product.add.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.admin.product.add.dao.ProductAddDAO;
import com.mall.admin.product.add.service.ProductAddService;
import com.mall.common.pojo.Product;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/*
 *   作者：官宣轩
 *   日期：2020-09-25
 */
@Service
public class ProductAddServiceImpl implements ProductAddService {

    @Resource
    private ProductAddDAO productAddDAO;
    public void setProductAddDAO(ProductAddDAO productAddDAO) {
        this.productAddDAO = productAddDAO;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productAdd(Product product) {
        boolean b = productAddDAO.productAdd(product);
        if (b){
            stringRedisTemplate.delete("productList");
            stringRedisTemplate.delete("producAlltList");
        }
        return b;
    }

    @Override
    public String searchCompanyIdByAdminUUID(String adminId) {
        String companyId = "";
        try {
            String s = (String) stringRedisTemplate.boundHashOps("searchCompanyIdByAdminUUID").get("adminId-" + adminId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("searchCompanyIdByAdminUUID").get("adminId-" + adminId);
                    if (s == null) {
                        companyId = productAddDAO.searchCompanyIdByAdminUUID(adminId);
                        String jsonStr = mapper.writeValueAsString(companyId);
                        stringRedisTemplate.boundHashOps("searchCompanyIdByAdminUUID").put("adminId-"+adminId,jsonStr);
                    }
                }
            }else {
                companyId = mapper.readValue(s, String.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyId;
    }
}

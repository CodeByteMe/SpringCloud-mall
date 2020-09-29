package com.mall.admin.product.attribute.category.list.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.product.attribute.category.list.dao.ProductAttributeListDAO;
import com.mall.admin.product.attribute.category.list.service.ProductAttributeListService;
import com.mall.common.pojo.ProductAttribute;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
@Service
public class ProductAttributeListServiceImpl implements ProductAttributeListService {

    @Resource
    private ProductAttributeListDAO productAttributeListDAO;

    public void setProductAttributeListDAO(ProductAttributeListDAO productAttributeListDAO) {
        this.productAttributeListDAO = productAttributeListDAO;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public PageInfo productAttributeListById(int productAttributeCategoryId, int pageNum, int pageSize) {
        List<ProductAttribute> productAttributes = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            stringRedisTemplate.delete("productAttributeListById");
            String s = (String) stringRedisTemplate.boundHashOps("productAttributeListById").get("productAttributeListById-" + pageNum);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productAttributeListById").get("productAttributeListById-" + pageNum);
                    if (s == null) {
                        productAttributes = productAttributeListDAO.productAttributeListById(productAttributeCategoryId);
                        String jsonStr = mapper.writeValueAsString(productAttributes);
                        stringRedisTemplate.boundHashOps("productAttributeListById").put("productAttributeListById-" + pageNum, jsonStr);
                    }
                }
            } else {
                productAttributes = mapper.readValue(s, new TypeReference<List<ProductAttribute>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(productAttributes);
        return pageInfo;
    }

    @Override
    public PageInfo productParameterListById(int productAttributeCategoryId, int pageNum, int pageSize) {
        List<ProductAttribute> productAttributes = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            String s = (String) stringRedisTemplate.boundHashOps("productParameterListById").get("productParameterListById-" + pageNum);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productParameterListById").get("productParameterListById-" + pageNum);
                    if (s == null) {
                        productAttributes = productAttributeListDAO.productParameterListById(productAttributeCategoryId);
                        String jsonStr = mapper.writeValueAsString(productAttributes);
                        stringRedisTemplate.boundHashOps("productParameterListById").put("productParameterListById-" + pageNum, jsonStr);
                    }
                }
            } else {
                productAttributes = mapper.readValue(s, new TypeReference<List<ProductAttribute>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(productAttributes);
        return pageInfo;
    }
}

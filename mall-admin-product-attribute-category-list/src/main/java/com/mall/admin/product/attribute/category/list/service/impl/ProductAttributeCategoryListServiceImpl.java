package com.mall.admin.product.attribute.category.list.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.product.attribute.category.list.dao.ProductAttributeCategoryListDAO;
import com.mall.admin.product.attribute.category.list.service.ProductAttributeCategoryListService;
import com.mall.common.pojo.ProductAttributeCategory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
@Service
public class ProductAttributeCategoryListServiceImpl implements ProductAttributeCategoryListService {

    @Resource
    private ProductAttributeCategoryListDAO productAttributeCategoryListDAO;
    public void setProductAttributeCategoryListDAO(ProductAttributeCategoryListDAO productAttributeCategoryListDAO) {
        this.productAttributeCategoryListDAO = productAttributeCategoryListDAO;
    }
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public PageInfo productAttributeCategoryList(int pageNum, int pageSize) {
        List<ProductAttributeCategory> productAttributeCategories = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            stringRedisTemplate.delete("productAttributeCategoryList");
            String s = (String) stringRedisTemplate.boundHashOps("productAttributeCategoryList").get("productAttributeCategoryList-"+pageNum+"pageSize"+pageSize);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productAttributeCategoryList").get("productAttributeCategoryList-"+pageNum+"pageSize"+pageSize);
                    if (s == null) {
                        productAttributeCategories = productAttributeCategoryListDAO.productAttributeCategoryList();
                        String jsonStr = mapper.writeValueAsString(productAttributeCategories);
                        stringRedisTemplate.boundHashOps("productAttributeCategoryList").put("productAttributeCategoryList-"+pageNum+"pageSize"+pageSize,jsonStr);
                    }
                }
            }else {
                productAttributeCategories = mapper.readValue(s, new TypeReference<List<ProductAttributeCategory>>() {
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(productAttributeCategories);
        return pageInfo;
    }

    @Override
    public List<ProductAttributeCategory> productAttributeCategoryListAll() {
        List<ProductAttributeCategory> productAttributeCategories = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("productAttributeCategoryListAll").get("productAttributeCategoryListAll");
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productAttributeCategoryListAll").get("productAttributeCategoryListAll");
                    if (s == null) {
                        productAttributeCategories = productAttributeCategoryListDAO.productAttributeCategoryList();
                        String jsonStr = mapper.writeValueAsString(productAttributeCategories);
                        stringRedisTemplate.boundHashOps("productAttributeCategoryListAll").put("productAttributeCategoryListAll",jsonStr);
                    }
                }
            }else {
                productAttributeCategories = mapper.readValue(s, new TypeReference<List<ProductAttributeCategory>>() {
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productAttributeCategories;
    }
}

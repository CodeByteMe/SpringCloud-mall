package com.mall.admin.product.category.list.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.product.category.list.dao.ProductCategoryListDAO;
import com.mall.admin.product.category.list.service.ProductCategoryListService;
import com.mall.common.pojo.ProductCategory;
import com.mall.common.vo.OptionsVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
@Service
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    @Resource
    private ProductCategoryListDAO productCategoryListDAO;
    public void setProductCategoryListDAO(ProductCategoryListDAO productCategoryListDAO) {
        this.productCategoryListDAO = productCategoryListDAO;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public PageInfo productCategoryList(int pageNum, int pageSize) {
        List<ProductCategory> productCategories = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            stringRedisTemplate.delete("productCategoryList");
            String s = (String) stringRedisTemplate.boundHashOps("productCategoryList").get("productCategoryList");
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productCategoryList").get("productCategoryList");
                    if (s == null) {
                        productCategories = productCategoryListDAO.productCategoryList();
                        String jsonStr = mapper.writeValueAsString(productCategories);
                        stringRedisTemplate.boundHashOps("productCategoryList").put("productCategoryList", jsonStr);
                    }
                }
            } else {
                productCategories = mapper.readValue(s, new TypeReference<List<ProductCategory>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(productCategories);
        return pageInfo;
    }

    @Override
    public PageInfo productCategoryListByParentId(int parentId, int pageNum, int pageSize) {
        List<ProductCategory> productCategories = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            stringRedisTemplate.delete("productCategoryListByParentId");
            String s = (String) stringRedisTemplate.boundHashOps("productCategoryListByParentId").get("productCategoryListByParentId-"+pageNum+"pageSize"+pageSize);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productCategoryListByParentId").get("productCategoryListByParentId-"+pageNum+"pageSize"+pageSize);
                    if (s == null) {
                        productCategories = productCategoryListDAO.productCategoryListByParentId(parentId);
                        String jsonStr = mapper.writeValueAsString(productCategories);
                        stringRedisTemplate.boundHashOps("productCategoryListByParentId").put("productCategoryListByParentId-"+pageNum+"pageSize"+pageSize, jsonStr);
                    }
                }
            } else {
                productCategories = mapper.readValue(s, new TypeReference<List<ProductCategory>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(productCategories);
        return pageInfo;
    }

    @Override
    public List<OptionsVO> productCategoryOptions() {
        List<OptionsVO> optionsVOS = null;
        try {
            stringRedisTemplate.delete("productCategoryOptions");
            String s = (String) stringRedisTemplate.boundHashOps("productCategoryOptions").get("productCategoryOptions");
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productCategoryOptions").get("productCategoryOptions");
                    if (s == null) {
                        optionsVOS = productCategoryListDAO.productCategoryOptions();
                        String jsonStr = mapper.writeValueAsString(optionsVOS);
                        stringRedisTemplate.boundHashOps("productCategoryOptions").put("productCategoryOptions", jsonStr);
                    }
                }
            } else {
                optionsVOS = mapper.readValue(s, new TypeReference<List<OptionsVO>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionsVOS;
    }


}

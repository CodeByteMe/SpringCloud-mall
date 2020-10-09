package com.mall.product.list.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Product;
import com.mall.common.pojo.SkuStock;
import com.mall.product.list.dao.ProductListDAO;
import com.mall.product.list.service.ProductListService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
@Service
public class ProductListServiceImpl implements ProductListService {

    @Resource
    private ProductListDAO productListDAO;
    public void setProductListDAO(ProductListDAO productListDAO) {
        this.productListDAO = productListDAO;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public PageInfo productList(int pageNum, int pageSize,String adminId) {
        List<Product> products = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            stringRedisTemplate.delete("productList");
            String s = (String) stringRedisTemplate.boundHashOps("productList").get("adminId-"+adminId+"pageNum="+pageNum);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productList").get("adminId-"+adminId+"pageNum="+pageNum);
                    if (s == null) {
                        products = productListDAO.productList(adminId);
                        String jsonStr = mapper.writeValueAsString(products);
                        stringRedisTemplate.boundHashOps("productList").put("adminId-"+adminId+"pageNum="+pageNum, jsonStr);
                    }else {
                        products = mapper.readValue(s, new TypeReference<List<Product>>() {
                        });
                    }
                }
            } else {
                products = mapper.readValue(s, new TypeReference<List<Product>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;
    }

    @Override
    public Product productDetailByProductId(String productId) {
        Product product = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("productDetailByProductId").get("productDetailByProductId-" + productId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productDetailByProductId").get("productDetailByProductId-" + productId);
                    if (s == null) {
                        product = productListDAO.productDetailByProductId(productId);
                        String jsonStr = mapper.writeValueAsString(product);
                        stringRedisTemplate.boundHashOps("productDetailByProductId").put("productDetailByProductId-" + productId, jsonStr);
                    }
                }
            } else {
                product = mapper.readValue(s, new TypeReference<Product>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<SkuStock> getSkuStockByProductId(String productId) {
        return productListDAO.getSkuStockByProductId(productId);
    }

    @Override
    public PageInfo producAlltList(Integer page, Integer pageSize) {
        List<Product> products = null;
        PageHelper.startPage(page, pageSize);
        try {
            stringRedisTemplate.delete("producAlltList");
            String s = (String) stringRedisTemplate.boundHashOps("producAlltList").get("producAlltList-"+page);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("producAlltList").get("producAlltList-"+page);
                    if (s == null) {
                        products = productListDAO.productAllList();
                        String jsonStr = mapper.writeValueAsString(products);
                        stringRedisTemplate.boundHashOps("producAlltList").put("producAlltList-"+page, jsonStr);
                    }
                }
            } else {
                products = mapper.readValue(s, new TypeReference<List<Product>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;
    }
}

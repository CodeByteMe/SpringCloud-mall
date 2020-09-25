package com.mall.address.list.service.impl;

import com.mall.address.list.dao.AddressDAO;
import com.mall.address.list.service.AddressService;
import com.mall.common.pojo.Address;
import com.mall.common.pojo.City;
import com.mall.common.pojo.Province;
import com.mall.common.pojo.Region;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDAO addressDAO;

    @Override
    public List<Address> getAllListByMemberId(String memberId) {
        return addressDAO.getAllListByMemberId(memberId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int deleteByIdAndMemberId(String memberId) {
        return addressDAO.deleteByIdAndMemberId(memberId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int insertAddress(Address address) {
        return addressDAO.insertAddress(address);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int updateAddress(Address address) {
        return addressDAO.updateAddress(address);
    }

    @Override
    public List<Province> listProvince() {
        return addressDAO.listProvince();
    }

    @Override
    public List<City> listCityByProvince(String provinceId) {
        return addressDAO.listCityByProvince(provinceId);
    }

    @Override
    public List<Region> listRegionByCity(String cityId) {
        return addressDAO.listRegionByCity(cityId);
    }

    @Override
    public Province getProvince(String provinceId) {
        return addressDAO.getProvince(provinceId);
    }

    @Override
    public City getCity(String cityId) {
        return addressDAO.getCity(cityId);
    }

    @Override
    public Region getRegion(String regionId) {
        return addressDAO.getRegion(regionId);
    }
}

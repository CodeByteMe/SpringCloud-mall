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
    public boolean updateAddress(Address address) {
        return addressDAO.updateAddress(address)>0;
    }

    /**
     * 修改用户的默认地址，一个用户只有一个默认地址，
     * 如果该用户之前设置了默认地址则去查询这个用户的默认地址的地址ID，
     * 将其修改为不是默认地址，再将用户新修改的这条地址信息修改为默认地址
     * @param addressId
     * @param defaultStatus
     * @param memberId
     * @return
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateAddressStatus(String addressId,int defaultStatus,String memberId){
        String s = addressDAO.selectAddressIdByDefaultStatus(1, memberId);
        if(s!=null){
            addressDAO.updateAddressStatus(s,0, memberId);
            return addressDAO.updateAddressStatus(addressId,1,memberId)>0;
        }else {
            return addressDAO.updateAddressStatus(addressId,1,memberId)>0;
        }
    }

    @Override
    public String selectAddressIdByDefaultStatus(int defaultStatus, String memberId) {
        return addressDAO.selectAddressIdByDefaultStatus(defaultStatus,memberId);
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

    @Override
    public List<Address> getAddressByAddressId(String addressId) {
        return addressDAO.getAddressByAddressId(addressId);
    }
}

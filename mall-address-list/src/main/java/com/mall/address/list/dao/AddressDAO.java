package com.mall.address.list.dao;

import com.mall.common.pojo.Address;
import com.mall.common.pojo.City;
import com.mall.common.pojo.Province;
import com.mall.common.pojo.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDAO {
    /**
     * 根据前台用户ID查询该用户所有地址信息
     * @param memberId
     * @return
     */
    public List<Address> getAllListByMemberId(String memberId);

    /**
     * 根据地址ID删除地址信息
     * @param addressId
     * @return
     */
    public int deleteByIdAndMemberId(String addressId);

    /**
     * 添加地址
     * @param address
     * @return
     */
    public int insertAddress(Address address);

    /**
     * 更新地址信息
     * @param address
     * @return
     */
    public int updateAddress(Address address);

    /**
     * 用户更改地址为默认
     * @param addressId
     * @param defaultStatus
     * @param memberId
     * @return
     */
    public int updateAddressStatus(@Param("addressId")String addressId,
                                   @Param("defaultStatus")int defaultStatus,
                                   @Param("memberId")String memberId);

    /**
     * 查询用户的默认地址
     * @param defaultStatus
     * @param memberId
     * @return
     */
    public String selectAddressIdByDefaultStatus( @Param("defaultStatus")int defaultStatus,
                                      @Param("memberId")String memberId);

    /**
     * 查询所有的省份
     * @return
     */
    public List<Province> listProvince();

    /**
     * 根据省份id查询城市
     * @param provinceId
     * @return
     */
    public List<City> listCityByProvince(String provinceId);

    /**
     * 根据城市id查区
     * @param cityId
     * @return
     */
    public List<Region> listRegionByCity(String cityId);

    /**
     * 根据省份id查省份信息
     * @param provinceId
     * @return
     */
    public Province getProvince(String provinceId);

    /**
     * 根据城市id查询城市信息
     * @param cityId
     * @return
     */
    public City getCity(String cityId);

    /**
     * 根据区id查询区信息
     * @param regionId
     * @return
     */
    public Region getRegion(String regionId);
}

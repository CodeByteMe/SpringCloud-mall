package com.mall.admin.order.apply.dao;

import com.mall.common.pojo.OrderReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ApplyDAO
 *
 * @Author BessCroft
 * @Date 2020/9/25 20:04
 */
public interface ApplyDAO {
    /**
     * 查询所有的订单退货申请（只能查询自己公司的，自己权限有的）
     * @return
     */
    public List<OrderReturn> listOrderReturn(String adminId);

    /**
     * 根据申请表的id删除一条数据
     * @param id
     * @return
     */
    public int deleteOrderReturn(Integer id);

    /**
     * 根据申请id，修改申请的状态
     * @param id
     * @param status
     * @return
     */
    public int updateApplyStatus(@Param("id") Integer id,
                                 @Param("status") Integer status);
}

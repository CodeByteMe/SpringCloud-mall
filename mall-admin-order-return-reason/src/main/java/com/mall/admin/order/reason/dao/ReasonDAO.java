package com.mall.admin.order.reason.dao;

import com.mall.common.pojo.FlashPromotionProductRelation;
import com.mall.common.pojo.OrderReturnReason;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ReasonDAO
 *
 * @Author BessCroft
 * @Date 2020/9/25 17:11
 */
public interface ReasonDAO {
    /**
     * 查询FlashPromotionProductRelation表中的所有信息
     * @return
     */
    public List<OrderReturnReason> listOrderReturnReason();

    /**
     * 更新退货理由的启用状态
     * @param status
     * @return
     */
    public int updateStatus(@Param("status") Integer status,
                            @Param("id") Integer id);

    /**
     * 根据id删除退货理由
     * @param id
     * @return
     */
    public int deleteReason(Integer id);

    /**
     * 新增退货理由
     * @param reason
     * @return
     */
    public int insertReason(OrderReturnReason reason);
}

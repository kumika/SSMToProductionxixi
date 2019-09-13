package Com.CB.Production.Mapper;

import Com.CB.Production.domain.COrder;
import Com.CB.Production.domain.COrderExample;
import Com.CB.Production.domain.vo.COrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface COrderMapper {
    long countByExample(COrderExample example);

    int deleteByExample(COrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(COrder record);

    int insertSelective(COrder record);

    List<COrderVo> selectByExample(COrderExample example);

    COrderVo selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") COrder record, @Param("example") COrderExample example);

    int updateByExample(@Param("record") COrder record, @Param("example") COrderExample example);

    int updateByPrimaryKeySelective(COrder record);

    int updateByPrimaryKey(COrder record);

    /*==============自己添加的方法===================================================================*/
    //扩展的mapper接口方法
    List<COrderVo> find(COrderVo cOrderVo);

    //修改订单要求
    int updateNote(COrder cOrder);

    int deleteBatch(String[] ids);

    int changeStatus(String[] ids);

    /*查询订单根据订单id*/
    List<COrderVo> searchOrderByOrderId(String orderId);

    /*根据客户名称查找*/
    List<COrderVo> searchOrderByCustom(String customName);

    /*根据产品名称查找*/
    List<COrderVo> searchOrderByProductName(String productName);
}
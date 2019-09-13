package Com.CB.Production.service.impl.scheduling;

import Com.CB.Production.Mapper.COrderMapper;
import Com.CB.Production.domain.COrder;
import Com.CB.Production.domain.COrderExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.COrderVo;
import Com.CB.Production.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceIMPL implements OrderService {

    @Autowired
    COrderMapper cOrderMapper;

    @Override
    public COrderVo get(String orderId) {
        return cOrderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<COrderVo> find() {
        COrderExample example = new COrderExample();
        List<COrderVo> cOrderVos = cOrderMapper.selectByExample(example);

        return cOrderVos;
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, COrderVo cOrderVo) {

        //分页处理
        PageHelper.startPage(page, rows);
        //这个查询使用了LEFT join  表格级联 =============================注意
        List<COrderVo> list = cOrderMapper.find(cOrderVo);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        //取记录总条数
        PageInfo<COrderVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public CustomResult insert(COrder cOrder) {

        int i = cOrderMapper.insert(cOrder);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增订单失败");
        }
    }

    @Override
    public CustomResult update(COrder cOrder) {
        int i = cOrderMapper.updateByPrimaryKeySelective(cOrder);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "修改订单失败");
        }
    }

    @Override
    public CustomResult updateAll(COrder cOrder) {
        int i = cOrderMapper.updateByPrimaryKey(cOrder);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "修改订单失败");
        }
    }

    @Override
    public CustomResult updateNote(COrder cOrder) {
        int i = cOrderMapper.updateNote(cOrder);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "修改订单要求失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = cOrderMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {

        int i = cOrderMapper.deleteBatch(ids);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return null;
        }
    }

    @Override
    public CustomResult changeStatus(String[] ids) {
        int i = cOrderMapper.changeStatus(ids);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return null;
        }
    }

    @Override
    public EUDataGridResult searchOrderByOrderId(Integer page, Integer rows, String orderId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<COrderVo> list = cOrderMapper.searchOrderByOrderId(orderId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<COrderVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchOrderByCustom(Integer page, Integer rows, String customName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<COrderVo> list = cOrderMapper.searchOrderByCustom(customName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<COrderVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchOrderByProductName(Integer page, Integer rows, String productName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<COrderVo> list = cOrderMapper.searchOrderByProductName(productName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<COrderVo> pageInfo = new PageInfo<>();
        result.setTotal(pageInfo.getTotal());

        return result;
    }


}

package Com.CB.Production.service.impl.quality;

import Com.CB.Production.Mapper.FinalCountCheckMapper;
import Com.CB.Production.domain.FinalCountCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.FinalCountCheckVo;
import Com.CB.Production.service.FCountCheckService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FCountCheckServiceIMP implements FCountCheckService {

    @Autowired
    FinalCountCheckMapper finalCountCheckMapper;


    @Override
    public FinalCountCheck get(String finalCountCheckId) {
        return finalCountCheckMapper.selectByPrimaryKey(finalCountCheckId);
    }

    @Override
    public CustomResult insert(FinalCountCheck finalCountCheck) {
        System.out.println("hahahahahahh");
        int i = finalCountCheckMapper.insert(finalCountCheck);
        System.out.println("ddddddddddddd");
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增成品计数质检信息失败");
        }
    }

    @Override
    public CustomResult updateAll(FinalCountCheck finalCountCheck) {
        int i = finalCountCheckMapper.updateByPrimaryKey(finalCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改成品计数质检信息失败");
        }
    }

    @Override
    public CustomResult updateNote(FinalCountCheck finalCountCheck) {
        int i = finalCountCheckMapper.updateNote(finalCountCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改成品计数质检备注失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = finalCountCheckMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, FinalCountCheck finalCountCheck) {
        PageHelper.startPage(page, rows);
        List<FinalCountCheckVo> list = finalCountCheckMapper.find(finalCountCheck);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<FinalCountCheckVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchFCountCheckByFCountCheckId(Integer page, Integer rows, String fCountCheckId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<FinalCountCheckVo> list = finalCountCheckMapper.searchFCountCheckByFCountCheckId(fCountCheckId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<FinalCountCheckVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchFCountCheckByOrderId(Integer page, Integer rows, String orderId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<FinalCountCheckVo> list = finalCountCheckMapper.searchFCountCheckByOrderId(orderId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<FinalCountCheckVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}

package Com.CB.Production.service.impl.quality;

import Com.CB.Production.Mapper.FinalMeasuretCheckMapper;
import Com.CB.Production.domain.FinalMeasuretCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.FinalMeasuretCheckVo;
import Com.CB.Production.service.MeasureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureServiceIMP implements MeasureService {

    @Autowired
    FinalMeasuretCheckMapper finalMeasuretCheckMapper;


    @Override
    public FinalMeasuretCheck get(String finalMeasuretCheckId) {
        return finalMeasuretCheckMapper.selectByPrimaryKey(finalMeasuretCheckId);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, FinalMeasuretCheck finalMeasuretCheck) {
        PageHelper.startPage(page, rows);
        List<FinalMeasuretCheckVo> list = finalMeasuretCheckMapper.find(finalMeasuretCheck);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<FinalMeasuretCheckVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(FinalMeasuretCheck finalMeasuretCheck) {
        System.out.println("hahahahahahh");
        int i = finalMeasuretCheckMapper.insert(finalMeasuretCheck);
        System.out.println("ddddddddddddd");
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增成品计量质检信息失败");
        }
    }

    @Override
    public CustomResult updateAll(FinalMeasuretCheck finalMeasuretCheck) {
        int i = finalMeasuretCheckMapper.updateByPrimaryKey(finalMeasuretCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改成品计量质检信息失败");
        }
    }

    @Override
    public CustomResult updateNote(FinalMeasuretCheck finalMeasuretCheck) {

        int i = finalMeasuretCheckMapper.updateNote(finalMeasuretCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改成品计量质检备注失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        return null;
    }

    @Override
    public EUDataGridResult searchFMeasureCheckByFMeasureCheckId(Integer page, Integer rows, String fMeasureCheckId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<FinalMeasuretCheckVo> list = finalMeasuretCheckMapper.searchFMeasureCheckByFMeasureCheckId(fMeasureCheckId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<FinalMeasuretCheckVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchFMeasureCheckByOrderId(Integer page, Integer rows, String orderId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<FinalMeasuretCheckVo> list = finalMeasuretCheckMapper.searchFMeasureCheckByOrderId(orderId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<FinalMeasuretCheckVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}

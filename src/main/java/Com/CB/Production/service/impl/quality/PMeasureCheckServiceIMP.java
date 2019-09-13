package Com.CB.Production.service.impl.quality;

import Com.CB.Production.Mapper.ProcessMmeasureCheckMapper;
import Com.CB.Production.domain.ProcessMmeasureCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.ProcessMeasureCheckVO;
import Com.CB.Production.service.PMeasureCheckService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PMeasureCheckServiceIMP implements PMeasureCheckService {

    @Autowired
    ProcessMmeasureCheckMapper processMmeasureCheckMapper;

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, ProcessMmeasureCheck processMeasureCheck) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<ProcessMeasureCheckVO> list = processMmeasureCheckMapper.find(processMeasureCheck);

        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<ProcessMeasureCheckVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(ProcessMmeasureCheck processMeasureCheck) {
        int i = processMmeasureCheckMapper.insert(processMeasureCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增工序计量质检信息失败");
        }
    }

    @Override
    public CustomResult updateAll(ProcessMmeasureCheck processMeasureCheck) {
        int i = processMmeasureCheckMapper.updateByPrimaryKey(processMeasureCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改工序计量质检信息失败");
        }
    }

    @Override
    public CustomResult updateNote(ProcessMmeasureCheck processMeasureCheck) {
        int i = processMmeasureCheckMapper.updateNote(processMeasureCheck);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改工序计量质检备注失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = processMmeasureCheckMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult searchPMeasureCheckByPMeasureCheckId(Integer page, Integer rows, String pMeasureCheckId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<ProcessMeasureCheckVO> list = processMmeasureCheckMapper.searchPMeasureCheckByPMeasureCheckId(pMeasureCheckId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<ProcessMeasureCheckVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}

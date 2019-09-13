package Com.CB.Production.service.impl.scheduling;


import Com.CB.Production.Mapper.ManufactureMapper;
import Com.CB.Production.domain.Manufacture;
import Com.CB.Production.domain.ManufactureExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.ManufactureVo;
import Com.CB.Production.service.ManufactureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactureServiceIMPL implements ManufactureService{



    @Autowired
    ManufactureMapper manufactureMapper;


    @Override
    public ManufactureVo get(String manufactureId) {
        return manufactureMapper.selectByPrimaryKey(manufactureId);
    }

    @Override
    public List<ManufactureVo> find() {
        ManufactureExample example = new ManufactureExample();
        return manufactureMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);
        List<ManufactureVo> list = manufactureMapper.find();

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        PageInfo<ManufactureVo> pageInfo = new PageInfo<>();
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public CustomResult insert(Manufacture manufacture) {
        int i = manufactureMapper.insert(manufacture);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增生产计划信息失败");
        }
    }

    @Override
    public CustomResult update(Manufacture manufacture) {
        int i  = manufactureMapper.updateByPrimaryKeySelective(manufacture);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改生产计划信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Manufacture manufacture) {
        int i =manufactureMapper.updateByPrimaryKey(manufacture);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改生产计划信息失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = manufactureMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = manufactureMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    /*根据生产流水号查找*/
    @Override
    public EUDataGridResult searchManufactureByManufactureSN(Integer page, Integer rows, String manufactureSn) {
        PageHelper.startPage(page, rows);
        List<ManufactureVo> manufactureVoList = manufactureMapper.searchManufactureByManufactureSN(manufactureSn);

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(manufactureVoList);

        PageInfo<ManufactureVo> pageInfo = new PageInfo<>(manufactureVoList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchManufactureByManufactureOrderId(Integer page, Integer rows, String manufactureOrderId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<ManufactureVo> manufactureVoList = manufactureMapper.searchManufactureByManufactureOrderId(manufactureOrderId);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(manufactureVoList);
        //取记录总条数
        PageInfo<ManufactureVo> pageInfo = new PageInfo<>(manufactureVoList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchManufactureByManufactureTechnologyName(Integer page, Integer rows, String manufactureTechnologyName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<ManufactureVo> manufactureVoList = manufactureMapper.searchManufactureByManufactureTechnologyName(manufactureTechnologyName);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(manufactureVoList);
        //取记录总条数
        PageInfo<ManufactureVo> pageInfo = new PageInfo<>(manufactureVoList);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }
}

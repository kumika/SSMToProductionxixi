package Com.CB.Production.service.impl.material;

import Com.CB.Production.Mapper.MaterialReceiveMapper;
import Com.CB.Production.domain.Material;
import Com.CB.Production.domain.MaterialReceive;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.MaterialReceiveVo;
import Com.CB.Production.service.MaterialReceiveService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialReceiveServiceIMP implements MaterialReceiveService {


    @Autowired
    MaterialReceiveMapper materialReceiveMapper;



    @Override
    public MaterialReceive get(String receiveId) {
        return materialReceiveMapper.selectByPrimaryKey(receiveId);
    }

    @Override
    public CustomResult insert(MaterialReceive materialReceive) {
        int i = materialReceiveMapper.insert(materialReceive);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增物料收入信息失败");
        }
    }

    @Override
    public CustomResult update(MaterialReceive materialReceive) {
        int i = materialReceiveMapper.updateByPrimaryKeySelective(materialReceive);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改物料收入信息失败");
        }
    }

    @Override
    public CustomResult updateAll(MaterialReceive materialReceive) {
        int i = materialReceiveMapper.updateByPrimaryKey(materialReceive);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改物料收入信息失败");
        }
    }

    @Override
    public CustomResult updateNote(MaterialReceive materialReceive) {
        int i = materialReceiveMapper.updateNote(materialReceive);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改物料收入备注失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = materialReceiveMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = materialReceiveMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<MaterialReceiveVo> list = materialReceiveMapper.find();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialReceiveVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchMaterialReceiveByReceiveId(Integer page, Integer rows, String receiveId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<MaterialReceiveVo> list = materialReceiveMapper.searchMaterialReceiveByReceiveId(receiveId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialReceiveVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchMaterialReceiveByMaterialId(Integer page, Integer rows, String materialId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<MaterialReceiveVo> list = materialReceiveMapper.searchMaterialReceiveByMaterialId(materialId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialReceiveVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}

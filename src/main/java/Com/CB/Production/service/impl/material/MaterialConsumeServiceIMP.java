package Com.CB.Production.service.impl.material;

import Com.CB.Production.Mapper.MaterialConsumeMapper;
import Com.CB.Production.domain.MaterialConsume;
import Com.CB.Production.domain.MaterialConsumeExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.MaterialConsumeVo;
import Com.CB.Production.service.MaterialConsumeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialConsumeServiceIMP implements MaterialConsumeService {

    @Autowired
    MaterialConsumeMapper materialConsumeMapper;

    @Override
    public MaterialConsume get(String orderId) {
        return materialConsumeMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<MaterialConsume> find() {
        MaterialConsumeExample example = new MaterialConsumeExample();
        return materialConsumeMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, MaterialConsumeVo materialConsume) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<MaterialConsumeVo> list = materialConsumeMapper.find(materialConsume);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialConsumeVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(MaterialConsume materialConsume) {
        int i = materialConsumeMapper.insert(materialConsume);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增物料消耗信息失败");
        }
    }

    @Override
    public CustomResult update(MaterialConsume materialConsume) {
        int i = materialConsumeMapper.updateByPrimaryKeySelective(materialConsume);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改物料消耗信息失败");
        }
    }

    @Override
    public CustomResult updateAll(MaterialConsume materialConsume) {
        int i = materialConsumeMapper.updateByPrimaryKey(materialConsume);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改物料消耗信息失败");
        }
    }

    @Override
    public CustomResult updateNote(MaterialConsume materialConsume) {
        int i = materialConsumeMapper.updateNote(materialConsume);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改物料消耗备注失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = materialConsumeMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = materialConsumeMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult searchMaterialConsumeByConsumeId(Integer page, Integer rows, String consumeId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<MaterialConsumeVo> list = materialConsumeMapper.searchMaterialConsumeByConsumeId(consumeId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialConsumeVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchMaterialConsumeByMaterialId(Integer page, Integer rows, String materialId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<MaterialConsumeVo> list = materialConsumeMapper.searchMaterialConsumeByMaterialId(materialId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialConsumeVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchMaterialConsumeByWorkId(Integer page, Integer rows, String workId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<MaterialConsumeVo> list = materialConsumeMapper.searchMaterialConsumeByWorkId(workId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialConsumeVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
